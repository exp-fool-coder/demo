package com.example.demo.configuration;

import com.example.demo.service.ProfileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManagersConfiguration {

    @Bean
    ProfileService profileService() {
        return new ProfileService();
    }
}
