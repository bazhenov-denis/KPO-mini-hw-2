package com.example.kpo_backend.presentation.controller;

import com.example.kpo_backend.application.service.FeedingOrganizationService;
import com.example.kpo_backend.domain.entity.Animal;
import com.example.kpo_backend.domain.entity.FeedingSchedule;
import com.example.kpo_backend.domain.valueobject.FoodType;
import com.example.kpo_backend.infrastructure.repository.AnimalRepository;
import com.example.kpo_backend.presentation.dto.FeedingScheduleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/api/feeding-schedule")
public class FeedingScheduleController {

    private final FeedingOrganizationService feedingOrganizationService;
    private final AnimalRepository animalRepository;

    public FeedingScheduleController(FeedingOrganizationService feedingOrganizationService,
                                     AnimalRepository animalRepository) {
        this.feedingOrganizationService = feedingOrganizationService;
        this.animalRepository = animalRepository;
    }

    @PostMapping("/{animalId}")
    public ResponseEntity<FeedingSchedule> scheduleFeeding(@PathVariable Long animalId,
                                                           @RequestBody FeedingScheduleRequest request) {
        Animal animal = animalRepository.findById(animalId);
        if (animal == null) {
            return ResponseEntity.notFound().build();
        }
        FeedingSchedule schedule = new FeedingSchedule(
                animal,
                LocalTime.parse(request.getFeedingTime()),
                FoodType.valueOf(request.getFoodType().toUpperCase())
        );
        feedingOrganizationService.scheduleFeeding(animalId, schedule);
        return ResponseEntity.ok(schedule);
    }

    @PostMapping("/complete/{scheduleId}")
    public ResponseEntity<Void> completeFeeding(@PathVariable Long scheduleId) {
        feedingOrganizationService.markFeedingAsCompleted(scheduleId);
        return ResponseEntity.ok().build();
    }
}
