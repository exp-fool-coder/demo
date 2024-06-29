package com.example.demo.controller;

import com.example.demo.controller.dto.JWTRefreshRequest;
import com.example.demo.controller.dto.JWTRequest;
import com.example.demo.controller.dto.JWTResponse;
import com.example.demo.manager.authorization.AuthorizationManager;
import com.example.demo.model.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthorizationManager authorizationManager;

    LoginController(AuthorizationManager authorizationManager) {
        this.authorizationManager = authorizationManager;
    }

    @PostMapping
    public JWTResponse login(@RequestBody JWTRequest request) {
        String login = request.getLogin();
        String password = request.getPassword();
        Jwt jwtTokens = authorizationManager.login(login, password);
        return new JWTResponse(
            jwtTokens.getToken(),
            jwtTokens.getRefreshToken()
        );
    }


    @PostMapping("/refresh")
    public JWTResponse refresh(@RequestBody JWTRefreshRequest request) {
        String refreshToken = request.getRefreshToken();

        Jwt jwtTokens = authorizationManager.refreshToken(refreshToken);

        return new JWTResponse(
            jwtTokens.getToken(),
            jwtTokens.getRefreshToken()
        );
    }
}
