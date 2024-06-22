package com.example.demo.controller;

import com.example.demo.controller.dto.CreateProfileRequest;
import com.example.demo.controller.dto.ProfileResponse;
import com.example.demo.controller.dto.UpdateProfileRequest;
import com.example.demo.controller.exception.ResourceNotFoundException;
import com.example.demo.entity.Profile;
import com.example.demo.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{profileId}")
    public ProfileResponse get(@PathVariable String profileId) {
        int id = Integer.parseInt(profileId);
        Profile profile = profileService.getById(id);
        if (profile == null) {
            throw new ResourceNotFoundException();
        }
        return profile.toProfileResponse();
    }

    @PostMapping
    public ProfileResponse create(@RequestBody CreateProfileRequest request) {
        String profileName = request.getName();
        Profile newProfile = profileService.create(profileName);
        return newProfile.toProfileResponse();
    }

    @PutMapping("/{profileId}")
    public ProfileResponse update(
            @PathVariable String profileId,
            @RequestBody UpdateProfileRequest request
    ) {
        int id = Integer.parseInt(profileId);
        Profile profile = profileService.getById(id);
        if (profile == null) {
            throw new ResourceNotFoundException();
        }
        profile.setName(request.getName());
        Profile updatedProfile = profileService.update(profile);
        return updatedProfile.toProfileResponse();
    }

    @DeleteMapping("/{profileId}")
    public String delete(@PathVariable String profileId) {
        int id = Integer.parseInt(profileId);
        Profile profile = profileService.getById(id);
        if (profile == null) {
            throw new ResourceNotFoundException();
        }
        profileService.deleteById(profile.getId());
        return "Success";
    }
}
