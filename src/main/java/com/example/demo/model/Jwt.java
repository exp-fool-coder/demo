package com.example.demo.model;

public class Jwt {

    private final String token;
    private final String refreshToken;

    public Jwt(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
