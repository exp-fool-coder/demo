package com.example.demo.model;

public enum ClaimField {
    LOGIN("login"),
    USER_ID("userId"),
    ROLES("roles");

    private String fieldName;

    ClaimField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
