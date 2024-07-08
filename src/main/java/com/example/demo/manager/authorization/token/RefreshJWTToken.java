package com.example.demo.manager.authorization.token;

import com.example.demo.entity.User;
import com.example.demo.model.ClaimField;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class RefreshJWTToken implements JWTToken{

    private final String signingKey;

    private final Long jwtExpiration;

    private SecretKey key;


    public RefreshJWTToken(String signingKey, Long jwtExpiration) {
        this.signingKey = signingKey;
        this.jwtExpiration = jwtExpiration;
    }

    private SecretKey secretKey() {
        if (key == null) {
            key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
        }
        return key;
    }

    @Override
    public String getToken(User user) {
        return Jwts.builder()
                .claims(
                        Map.of(
                                ClaimField.USER_ID.getFieldName(), user.getId()
                        )
                )
                .expiration(new Date(new Date().getTime() + jwtExpiration))
                .subject(user.getLogin())
                .signWith(secretKey())
                .compact();
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public boolean isValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
//        } catch (ExpiredJwtException expEx) {
//            log.error("Token expired", expEx);
//        } catch (UnsupportedJwtException unsEx) {
//            log.error("Unsupported jwt", unsEx);
//        } catch (MalformedJwtException mjEx) {
//            log.error("Malformed jwt", mjEx);
//        } catch (SignatureException sEx) {
//            log.error("Invalid signature", sEx);
//        } catch (Exception e) {
//            log.error("invalid token", e);
//        }
        } catch (JwtException e) {
            return false;
            //TODO:  make some more complexity
        }
    }
}
