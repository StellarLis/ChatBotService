package ru.andrew.testapi.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.andrew.testapi.model.interfaces.DatabaseDocument;
import ru.andrew.testapi.model.interfaces.DatabaseUser;
import ru.andrew.testapi.model.repo_model.Role;
import ru.andrew.testapi.model.service_model.ServiceUser;
import ru.andrew.testapi.service.interfaces.AuthenticationService;
import ru.andrew.testapi.service.interfaces.JwtService;
import ru.andrew.testapi.repository.repo_service.UserService;

import java.util.HashSet;
import java.util.Set;

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
        ServiceUser serviceUser = toServiceUser(user);
        String token = jwtService.generateToken(serviceUser);
        return token;
    }

    @Override
    public void upgradeAccount(String companyName) {
        DatabaseUser dbUser = userService.getCurrentUser();
        dbUser.setRole(Role.ROLE_COMPANY_MEMBER);
        dbUser.setCompanyName(companyName);
        ServiceUser serviceUser = toServiceUser(dbUser);
        userService.update(serviceUser);
    }

    private ServiceUser toServiceUser(DatabaseUser databaseUser) {
        return ServiceUser.builder()
                .id(databaseUser.getId())
                .username(databaseUser.getUsername())
                .password(databaseUser.getPassword())
                .email(databaseUser.getEmail())
                .documents(databaseUser.getDocuments())
                .role(databaseUser.getRole())
                .companyName(databaseUser.getCompanyName()).build();
    }
}
