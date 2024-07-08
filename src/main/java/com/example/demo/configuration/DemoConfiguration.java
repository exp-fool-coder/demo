package com.example.demo.configuration;

import com.example.demo.configuration.properties.AppProperties;
import com.example.demo.manager.authorization.AuthorizationManager;
import com.example.demo.manager.ProfileManager;
import com.example.demo.manager.UserManager;
import com.example.demo.manager.authorization.JWTProvider;
import com.example.demo.manager.authorization.JWTfilter;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        UserRepository userRepository,
        AppProperties appProperties
    ) {
        return new JWTProvider(
            userRepository,
            appProperties
        );
    }

    @Bean
    public JWTfilter jwtFilter(
            JWTProvider jwtProvider
    ) {
        return  new JWTfilter(jwtProvider);
    }

    @Bean
    public AppProperties appProperties(
            Environment environment
    ) {
        return new AppProperties(environment);
    }
}
