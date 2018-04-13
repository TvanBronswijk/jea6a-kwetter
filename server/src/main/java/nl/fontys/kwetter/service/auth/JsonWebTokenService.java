package nl.fontys.kwetter.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.fontys.kwetter.model.user.User;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JsonWebTokenService {

    public static String generateToken(User user) throws UnsupportedEncodingException, JsonProcessingException {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuer("Kwetter")
                .withIssuedAt(new Date())
                .withAudience(user.getUsername())
                .withExpiresAt(new Date(2020, 1, 1))
                .withClaim("user", new ObjectMapper().writeValueAsString(user))
                .sign(algorithm);
    }
}
