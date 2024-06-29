package com.example.demo.manager.authorization.token;

import com.example.demo.entity.User;
import com.example.demo.model.ClaimField;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RefreshJWTToken implements JWTToken{

    @Value("${jwt.signing.key.refresh}")
    private String signingKey;

    @Value("${jwt.key.refresh.expiration}")
    private Long jwtExpiration;

    private SecretKey key;


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
        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date());
    }
}
