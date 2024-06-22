package com.example.demo.service;

import com.example.demo.entity.Profile;
import com.example.demo.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Optional;

public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public Profile create(String name) {
        Profile profile = new Profile();
        profile.setName(name);
        return profileRepository.save(profile);
    }

    public Profile getById(int id) {
        Optional<Profile> profile = profileRepository.findById(id);
        return profile.orElse(null);
    }

    public Profile update(Profile profile) {
        profile.setUpdatedAt(Instant.now());
        return profileRepository.save(profile);
    }

    public void deleteById(int id) {
        profileRepository.deleteById(id);
    }
}
