package ru.andrew.testapi.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.andrew.testapi.model.service_model.ServiceUser;
import ru.andrew.testapi.service.interfaces.JwtService;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${token.signing.key}")
    private String signingKey;

    public String generateToken(ServiceUser user) throws JWTCreationException {
        Algorithm algorithm = Algorithm.HMAC256(signingKey);
        String token = JWT.create()
                .withIssuer("auth0")
                .withClaim("id", user.getId())
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getRole().ordinal())
                .withSubject(user.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
                .sign(algorithm);
        return token;
    }

    public String extractUsername(String token) throws JWTDecodeException {
        return JWT.decode(token).getSubject();
    }
}
