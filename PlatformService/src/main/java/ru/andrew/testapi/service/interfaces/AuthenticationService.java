package ru.andrew.testapi.service.interfaces;

public interface AuthenticationService {
    String signUp(
            String username,
            String password,
            String email
    ) throws Exception;
    String signIn(
            String username,
            String password
    ) throws Exception;
}
