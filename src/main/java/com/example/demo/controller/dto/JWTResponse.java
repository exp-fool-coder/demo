package com.example.demo.controller.dto;

public class JWTResponse {


    private final String type = "Bearer";
    private final String token;
    private final String refreshToken;

    public JWTResponse(String token, String refreshToken) {
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
