package com.example.demo.controller;

import com.example.demo.controller.dto.ProfileResponse;
import com.example.demo.controller.dto.RegisterRequest;
import com.example.demo.entity.Profile;
import com.example.demo.manager.authorization.AuthorizationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    AuthorizationManager authorizationManager;

    RegisterController(
        AuthorizationManager authorizationManager
    ) {
        this.authorizationManager = authorizationManager;
    }

    @PostMapping
    public @ResponseBody ProfileResponse register(@RequestBody RegisterRequest registerRequest) throws Exception {
        Profile newProfile = authorizationManager.registerNewAccount(
            registerRequest.getLogin(),
            registerRequest.getPassword(),
            registerRequest.getName()
        );
        return newProfile.toProfileResponse();
    }
}
