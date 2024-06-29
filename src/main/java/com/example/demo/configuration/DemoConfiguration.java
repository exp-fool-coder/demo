package com.example.demo.configuration;

import com.example.demo.manager.authorization.AuthorizationManager;
import com.example.demo.manager.ProfileManager;
import com.example.demo.manager.UserManager;
import com.example.demo.manager.authorization.JWTProvider;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoConfiguration {

    @Bean
    ProfileManager profileManager(
            ProfileRepository profileRepository
    ) {
        return new ProfileManager(
                profileRepository
        );
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserManager userManager() {
        return new UserManager();
    }

    @Bean
    AuthorizationManager authorizationManager(
            PasswordEncoder passwordEncoder,
            UserManager userManager,
            ProfileManager profileManager,
            JWTProvider jwtProvider
    ) {
        return new AuthorizationManager(
                passwordEncoder,
                userManager,
                profileManager,
                jwtProvider
        );
    }

    @Bean
    public JWTProvider jwtProvider(
        UserRepository userRepository
    ) {
        return new JWTProvider(
            userRepository
        );
    }
}
