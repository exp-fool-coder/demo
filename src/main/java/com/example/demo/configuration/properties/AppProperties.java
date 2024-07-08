package com.example.demo.configuration.properties;

import org.springframework.core.env.Environment;

import java.util.Objects;


public class AppProperties {

    public final Long jwtAccessTokenExpiration;
    public final String jwtAccessTokenSigningKey;
    public final Long jwtRefreshTokenExpiration;
    public final String jwtRefreshTokenSigningKey;

    public AppProperties(Environment environment) {
        this.jwtAccessTokenExpiration = Long.valueOf(Objects.requireNonNull(environment.getProperty("jwt.key.expiration")));
        this.jwtAccessTokenSigningKey = Objects.requireNonNull(environment.getProperty("jwt.signing.key"));
        this.jwtRefreshTokenExpiration = Long.valueOf(Objects.requireNonNull(environment.getProperty("jwt.key.refresh.expiration")));
        this.jwtRefreshTokenSigningKey = Objects.requireNonNull(environment.getProperty("jwt.signing.key.refresh"));
    }
}


