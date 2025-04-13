package com.example.kpo_backend.domain.valueobject;

public enum Gender {
    MALE("Самец"),
    FEMALE("Самка"),
    UNKNOWN("Неизвестно");

    private final String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}