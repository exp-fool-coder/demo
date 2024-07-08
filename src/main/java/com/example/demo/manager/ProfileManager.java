package com.example.demo.manager;

import com.example.demo.entity.Profile;
import com.example.demo.entity.User;
import com.example.demo.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Optional;

public class ProfileManager {

    ProfileRepository profileRepository;

    public ProfileManager(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile create(String name, User user) {
        Profile profile = new Profile();
        profile.setName(name);
        profile.setUser(user);
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
