package com.example.kpo_backend.application.event;

import java.time.LocalDateTime;

public class AnimalMovedEvent {
    private final Long animalId;
    private final Long oldEnclosureId;
    private final Long newEnclosureId;
    private final LocalDateTime moveTime;

    public AnimalMovedEvent(Long animalId, Long oldEnclosureId, Long newEnclosureId, LocalDateTime moveTime) {
        this.animalId = animalId;
        this.oldEnclosureId = oldEnclosureId;
        this.newEnclosureId = newEnclosureId;
        this.moveTime = moveTime;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public Long getOldEnclosureId() {
        return oldEnclosureId;
    }

    public Long getNewEnclosureId() {
        return newEnclosureId;
    }

    public LocalDateTime getMoveTime() {
        return moveTime;
    }
}
