package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping("/{profileId}")
    public String get(@PathVariable String profileId) {
        return "Profile " + profileId;
    }
}
