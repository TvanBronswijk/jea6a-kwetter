package nl.fontys.kwetter.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import nl.fontys.kwetter.model.auth.Token;
import nl.fontys.kwetter.model.user.Role;
import nl.fontys.kwetter.model.user.User;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JsonWebTokenService {
    private static final String ISSUER = "kwetter";
    private static final String KEY = "secret_key";


    public static Token generateToken(User user)
            throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(KEY);
        String token = JWT.create()
                .withSubject(user.getUsername())
                .withAudience(user.getUsername())
                .withIssuer(ISSUER)
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .withClaim("userId", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("isAdmin", user.isAdmin())
                .sign(algorithm);
        return new Token(token);
    }

    public static DecodedJWT parseToken(Token token)
            throws UnsupportedEncodingException, JWTVerificationException {

        Algorithm algorithm = Algorithm.HMAC256(KEY);
        return JWT.decode(token.getToken());
    }

    public static void verifyToken(Token token)
            throws UnsupportedEncodingException, JWTVerificationException {

        Algorithm algorithm = Algorithm.HMAC256(KEY);
        JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token.getToken());
    }
}
