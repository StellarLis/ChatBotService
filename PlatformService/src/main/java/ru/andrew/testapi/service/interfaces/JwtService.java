package ru.andrew.testapi.service.interfaces;

import ru.andrew.testapi.model.service_model.ServiceUser;

public interface JwtService {
    String generateToken(ServiceUser user) throws Exception;
    String extractUsername(String token) throws Exception;
}
