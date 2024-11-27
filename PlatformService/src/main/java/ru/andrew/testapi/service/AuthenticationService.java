package ru.andrew.testapi.service;

import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.andrew.testapi.dto.JwtAuthenticationResponse;
import ru.andrew.testapi.dto.SignInRequest;
import ru.andrew.testapi.dto.SignUpRequest;
import ru.andrew.testapi.model.Role;
import ru.andrew.testapi.model.User;
import ru.andrew.testapi.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public JwtAuthenticationResponse signUp(SignUpRequest request) throws JWTCreationException {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(token);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) throws JWTCreationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        var user = userService.loadUserByUsername(request.getUsername());
        var token = jwtService.generateToken((User) user);
        return new JwtAuthenticationResponse(token);
    }
}
