package ru.andrew.testapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.andrew.testapi.dto.*;
import ru.andrew.testapi.service.interfaces.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest request,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            AppErrorResponse response = new AppErrorResponse(400, errorMessage);
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
        }
        try {
            String token = authenticationService.signUp(
                    request.getUsername(),
                    request.getPassword(),
                    request.getEmail()
            );
            JwtAuthenticationResponse response = new JwtAuthenticationResponse(token);
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            AppErrorResponse response = new AppErrorResponse(400, e.getMessage());
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
        }
    }

    @Operation(summary = "Авторизация пользователя")
    @ApiResponses()
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInRequest request,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            AppErrorResponse response = new AppErrorResponse(400,
                    "Невалидные имя пользователя или пароль");
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(400));
        }
        try {
            String token = authenticationService.signIn(
                    request.getUsername(),
                    request.getPassword()
            );
            JwtAuthenticationResponse response = new JwtAuthenticationResponse(token);
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            AppErrorResponse response = new AppErrorResponse(400, e.getMessage());
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
        }
    }

    @Operation(summary = "Улучшение аккаунта пользователя до сотрудника компании")
    @PostMapping("/upgrade-account")
    public ResponseEntity<?> upgradeAccount(
            @RequestBody UpgradeRequest request
    ) {
        try {
            authenticationService.upgradeAccount(request.getCompanyName());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            AppErrorResponse response = new AppErrorResponse(400, e.getMessage());
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
        }
    }

}
