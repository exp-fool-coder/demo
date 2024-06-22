package com.example.demo.controller.dto;

import java.time.Instant;

public class ProfileResponse {

    private int id;

    private String name;

    private Instant createdAt;

    private Instant updatedAt;

    public ProfileResponse(int id, String name, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
