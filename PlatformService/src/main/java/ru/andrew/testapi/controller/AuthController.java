package ru.andrew.testapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;
import ru.andrew.testapi.dto.AppErrorResponse;
import ru.andrew.testapi.dto.JwtAuthenticationResponse;
import ru.andrew.testapi.dto.SignInRequest;
import ru.andrew.testapi.dto.SignUpRequest;
import ru.andrew.testapi.service.AuthenticationService;

import java.util.List;

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
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(400));
        }
        JwtAuthenticationResponse response = authenticationService.signUp(request);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
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
        JwtAuthenticationResponse response = authenticationService.signIn(request);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }
}
