package com.example.kpo_backend.presentation.controller;


import com.example.kpo_backend.application.service.AnimalTransferService;
import com.example.kpo_backend.application.service.FeedingOrganizationService;
import com.example.kpo_backend.application.service.ZooStatisticsService;
import com.example.kpo_backend.domain.entity.Animal;
import com.example.kpo_backend.domain.valueobject.AnimalSpecies;
import com.example.kpo_backend.domain.valueobject.FoodType;
import com.example.kpo_backend.domain.valueobject.Gender;
import com.example.kpo_backend.domain.valueobject.HealthStatus;
import com.example.kpo_backend.infrastructure.repository.AnimalRepository;
import com.example.kpo_backend.presentation.dto.AnimalRequest;
import com.example.kpo_backend.presentation.dto.ZooStatsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/animals")
public class AnimalController {

    private final AnimalTransferService animalTransferService;
    private final FeedingOrganizationService feedingOrganizationService;
    private final AnimalRepository animalRepository;
    private final ZooStatisticsService zooStatisticsService;

    public AnimalController(AnimalTransferService animalTransferService,
                            FeedingOrganizationService feedingOrganizationService,
                            AnimalRepository animalRepository,
                            ZooStatisticsService zooStatisticsService) {
        this.animalTransferService = animalTransferService;
        this.feedingOrganizationService = feedingOrganizationService;
        this.animalRepository = animalRepository;
        this.zooStatisticsService = zooStatisticsService;
    }


    @PostMapping
    public ResponseEntity<Animal> createAnimal(@RequestBody AnimalRequest request) {
        Animal animal = new Animal(
                request.getName(),
                AnimalSpecies.valueOf(request.getSpecies()),
                LocalDate.parse(request.getBirthDate()),
                Gender.valueOf(request.getGender().toUpperCase()),
                FoodType.valueOf(request.getFavoriteFood().toUpperCase()),
                HealthStatus.valueOf(request.getStatus().toUpperCase())
        );
        animalRepository.save(animal);
        return ResponseEntity.ok(animal);
    }


    @PostMapping("/{animalId}/move")
    public ResponseEntity<Void> moveAnimal(@PathVariable Long animalId,
                                           @RequestParam Long newEnclosureId) {
        animalTransferService.moveAnimal(animalId, newEnclosureId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/stats")
    public ResponseEntity<ZooStatsResponse> getStatistics() {
        ZooStatsResponse stats = new ZooStatsResponse(
                zooStatisticsService.getTotalAnimals(),
                zooStatisticsService.getSickAnimalsCount(),
                zooStatisticsService.getTotalEnclosures(),
                zooStatisticsService.getCompletedFeedings()
        );
        return ResponseEntity.ok(stats);
    }
}
