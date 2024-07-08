package com.example.demo.manager.authorization;

import com.example.demo.configuration.properties.AppProperties;
import com.example.demo.entity.User;
import com.example.demo.manager.authorization.token.AccessJWTToken;
import com.example.demo.manager.authorization.token.JWTToken;
import com.example.demo.manager.authorization.token.RefreshJWTToken;
import com.example.demo.model.ClaimField;
import com.example.demo.model.Jwt;
import com.example.demo.repository.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JWTProvider {

    private final JWTToken accessJWTToken;
    private final JWTToken refreshJWTToken;

    private final UserRepository userRepository;

    public JWTProvider(
            UserRepository userRepository,
            AppProperties appProperties
    ) {
        accessJWTToken = new AccessJWTToken(
                appProperties.jwtAccessTokenSigningKey,
                appProperties.jwtAccessTokenExpiration
        );
        refreshJWTToken = new RefreshJWTToken(
                appProperties.jwtRefreshTokenSigningKey,
                appProperties.jwtRefreshTokenExpiration
        );
        this.userRepository = userRepository;
    }

    public Jwt jwtAuth(User user) {
        String accessJwtToken = accessJWTToken.getToken(user);
        String refreshJwtToken =  refreshJWTToken.getToken(user);
        return new Jwt(
                accessJwtToken,
                refreshJwtToken
        );
    }

    public Jwt jwtRefresh(String refreshToken) throws RuntimeException {
        if (!refreshJWTToken.isValid(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        Claims refreshClaims = refreshJWTToken.getClaims(refreshToken);
        int userId = Integer.parseInt((String) refreshClaims.get(ClaimField.USER_ID.getFieldName()));
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        return jwtAuth(user.get());
    }

    public boolean isAccessTokenValid(String token) {
        if (!accessJWTToken.isValid(token)) {
            return false;
        }
        Claims claims = accessJWTToken.getClaims(token);
        int userId = Integer.parseInt((String) claims.get(ClaimField.USER_ID.getFieldName()));
        Optional<User> user = userRepository.findById(userId);

        return user.isPresent();
    }

    public Claims getAccessClaims(String token) {
        return accessJWTToken.getClaims(token);
    }
}
