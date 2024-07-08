package com.example.demo.manager.authorization;

import com.example.demo.entity.Profile;
import com.example.demo.entity.User;
import com.example.demo.manager.ProfileManager;
import com.example.demo.manager.UserManager;
import com.example.demo.model.Jwt;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthorizationManager {

    private final PasswordEncoder passwordEncoder;

    private final UserManager userManager;

    private final ProfileManager profileManager;

    private final JWTProvider jwtProvider;

    public AuthorizationManager(
            PasswordEncoder passwordEncoder,
            UserManager userManager,
            ProfileManager profileManager,
            JWTProvider jwtProvider
    ) {
        this.passwordEncoder = passwordEncoder;
        this.userManager = userManager;
        this.profileManager = profileManager;
        this.jwtProvider = jwtProvider;
    }

    public Jwt login(String login, String password) {
        User user = userManager.findByLogin(login);
        if (user == null) {
            throw new BadCredentialsException("Invalid username or password");
        }
        boolean isPasswordValid = passwordEncoder.matches(password, user.getPassword());
        if (!isPasswordValid) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return jwtProvider.jwtAuth(user);
    }


    public Profile registerNewAccount(
        String login,
        String password,
        String name
    ) throws Exception {
        User existingUser = userManager.findByLogin(login);
        if (existingUser != null) {
            //TODO:make some error response
            throw new Exception();
        }


        String encodedPassword = passwordEncoder.encode(password);
        User newUser = userManager.create(login, encodedPassword);

        return profileManager.create(name, newUser);
    }

    public Jwt refreshToken(String refreshToken) {
        return jwtProvider.jwtRefresh(refreshToken);
    }
}
