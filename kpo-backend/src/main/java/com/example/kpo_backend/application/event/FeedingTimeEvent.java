package com.example.kpo_backend.application.event;

import java.time.LocalDateTime;

public class FeedingTimeEvent {
    private final Long animalId;
    private final LocalDateTime feedingTime;

    public FeedingTimeEvent(Long animalId, LocalDateTime feedingTime) {
        this.animalId = animalId;
        this.feedingTime = feedingTime;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public LocalDateTime getFeedingTime() {
        return feedingTime;
    }
}
