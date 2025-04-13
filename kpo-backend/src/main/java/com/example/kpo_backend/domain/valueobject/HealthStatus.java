package com.example.kpo_backend.domain.valueobject;

public enum HealthStatus {
    HEALTHY("Здоров"),
    SICK("Болен");

    private final String description;

    HealthStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
