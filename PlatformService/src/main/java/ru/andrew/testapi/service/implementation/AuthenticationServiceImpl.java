package ru.andrew.testapi.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.andrew.testapi.model.interfaces.DatabaseUser;
import ru.andrew.testapi.model.repo_model.Role;
import ru.andrew.testapi.model.service_model.ServiceUser;
import ru.andrew.testapi.service.interfaces.AuthenticationService;
import ru.andrew.testapi.service.interfaces.JwtService;
import ru.andrew.testapi.repository.repo_service.UserService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String signUp(
            String username,
            String password,
            String email
    ) throws Exception {
        ServiceUser serviceUser = ServiceUser.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.ROLE_USER)
                .build();
        if (userService.existsByUsername(serviceUser.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (userService.existsByEmail(serviceUser.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        DatabaseUser dbUser = userService.save(serviceUser);
        serviceUser.setId(dbUser.getId());
        return jwtService.generateToken(serviceUser);
    }

    public String signIn(
            String username,
            String password
    ) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username,
                password
        ));
        DatabaseUser user = userService.getByUsername(username);
        ServiceUser serviceUser = ServiceUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .documents(user.getDocuments())
                .build();
        String token = jwtService.generateToken(serviceUser);
        return token;
    }
}
