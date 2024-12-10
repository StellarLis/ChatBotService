package ru.andrew.testapi.service.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.andrew.testapi.model.interfaces.DatabaseUser;
import ru.andrew.testapi.model.repo_model.Role;
import ru.andrew.testapi.model.repo_model.UserSQL;
import ru.andrew.testapi.repository.repo_service.UserService;
import ru.andrew.testapi.service.interfaces.JwtService;

import java.util.HashSet;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceImplTest {
    @InjectMocks
    private AuthenticationServiceImpl authenticationService;
    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;

    @Test
    void signUp_everythingCorrect_returnsToken() throws Exception {
        String expected = "sometoken";
        String username = "andrew";
        String password = "testpassword123";
        String email = "test@gmail.com";
        Mockito.when(passwordEncoder.encode(password)).thenReturn("encoded!");
        Mockito.when(userService.existsByUsername(username)).thenReturn(false);
        Mockito.when(userService.existsByEmail(email)).thenReturn(false);
        DatabaseUser dbUser = UserSQL.builder().id(1L).username("andrew")
                .password("testpassword123").email("test@gmail.com")
                .role(Role.ROLE_USER).build();
        Mockito.when(userService.save(ArgumentMatchers.any())).thenReturn(dbUser);
        Mockito.when(jwtService.generateToken(ArgumentMatchers.any())).thenReturn(expected);

        String actual = authenticationService.signUp(username, password, email);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void signUp_usernameExists_throwsException() {
        String username = "andrew";
        String password = "testpassword123";
        String email = "test@gmail.com";
        Mockito.when(passwordEncoder.encode(password)).thenReturn("encoded!");
        Mockito.when(userService.existsByUsername(username)).thenReturn(true);

        Assertions.assertThrows(Exception.class, () -> authenticationService.signUp(username, password, email));
    }

    @Test
    void signUp_emailExists_throwsException() {
        String username = "andrew";
        String password = "testpassword123";
        String email = "test@gmail.com";
        Mockito.when(passwordEncoder.encode(password)).thenReturn("encoded!");
        Mockito.when(userService.existsByUsername(username)).thenReturn(false);
        Mockito.when(userService.existsByEmail(email)).thenReturn(true);
        Assertions.assertThrows(Exception.class, () -> authenticationService.signUp(username, password, email));
    }

    @Test
    void signIn_everythingCorrect_returnsToken() throws Exception {
        String expected = "sometoken";
        String username = "andrew";
        String password = "testpassword123";
        DatabaseUser dbUser = UserSQL.builder().id(1L).username("andrew")
                        .password(password).email("test@gmail.com")
                        .role(Role.ROLE_USER).documents(new HashSet<>()).build();
        Mockito.when(userService.getByUsername(username))
                .thenReturn(dbUser);
        Mockito.when(jwtService.generateToken(ArgumentMatchers.any())).thenReturn("sometoken");
        Assertions.assertEquals(expected, authenticationService.signIn(username, password));
    }

    @Test
    void signIn_incorrectUsernameOrPassword_throwsException() throws Exception {
        String username = "andrew";
        String password = "testpassword123";
        Mockito.when(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        )).thenThrow(AuthenticationCredentialsNotFoundException.class);
        Assertions.assertThrows(AuthenticationException.class,
                () -> authenticationService.signIn(username, password));
    }

    @Test
    void upgradeAccount_allCorrect_doesntThrowsException() {
        String companyName = "test_company_name";
        DatabaseUser dbUser = UserSQL.builder().id(1L).username("andrew")
                .password("testpassword123").email("test@gmail.com")
                .documents(new HashSet<>()).build();
        Mockito.when(userService.getCurrentUser()).thenReturn(dbUser);
        authenticationService.upgradeAccount(companyName);
        Assertions.assertEquals(companyName, dbUser.getCompanyName());
        Assertions.assertEquals(Role.ROLE_COMPANY_MEMBER, dbUser.getRole());
    }
}
