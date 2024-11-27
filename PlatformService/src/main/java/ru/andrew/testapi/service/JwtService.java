package ru.andrew.testapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.andrew.testapi.model.User;

import java.util.Date;

@Service
public class JwtService {
    @Value("${token.signing.key}")
    private String signingKey;

    public String generateToken(User user) throws JWTCreationException {
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
