package com.example.kpo_backend.application.service;

import com.example.kpo_backend.application.event.DomainEventPublisher;
import com.example.kpo_backend.application.event.FeedingTimeEvent;
import com.example.kpo_backend.domain.entity.Animal;
import com.example.kpo_backend.domain.entity.FeedingSchedule;
import com.example.kpo_backend.infrastructure.repository.AnimalRepository;
import com.example.kpo_backend.infrastructure.repository.FeedingScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FeedingOrganizationService {

    private final AnimalRepository animalRepository;
    private final FeedingScheduleRepository feedingScheduleRepository;
    private final DomainEventPublisher eventPublisher;

    public FeedingOrganizationService(AnimalRepository animalRepository,
                                      FeedingScheduleRepository feedingScheduleRepository,
                                      DomainEventPublisher eventPublisher) {
        this.animalRepository = animalRepository;
        this.feedingScheduleRepository = feedingScheduleRepository;
        this.eventPublisher = eventPublisher;
    }

    public void scheduleFeeding(Long animalId, FeedingSchedule schedule) {
        Animal animal = animalRepository.findById(animalId);
        if (animal == null) {
            throw new IllegalArgumentException("Животное не найдено");
        }

        // Дополнительная валидация можно добавить, например, сравнение типа еды
        feedingScheduleRepository.save(schedule);
    }

    public void markFeedingAsCompleted(Long scheduleId) {
        FeedingSchedule schedule = feedingScheduleRepository.findById(scheduleId);
        if (schedule == null) {
            throw new IllegalArgumentException("Расписание кормления не найдено");
        }

        schedule.markAsCompleted();
        feedingScheduleRepository.save(schedule);

        FeedingTimeEvent event = new FeedingTimeEvent(schedule.getAnimal().getId(), LocalDateTime.now());
        eventPublisher.publish(event);
    }
}