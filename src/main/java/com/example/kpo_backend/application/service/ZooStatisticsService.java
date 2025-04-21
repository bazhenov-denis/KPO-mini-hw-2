package com.example.kpo_backend.application.service;

import com.example.kpo_backend.domain.entity.FeedingSchedule;
import com.example.kpo_backend.domain.valueobject.HealthStatus;
import com.example.kpo_backend.infrastructure.repository.AnimalRepository;
import com.example.kpo_backend.infrastructure.repository.EnclosureRepository;
import com.example.kpo_backend.infrastructure.repository.FeedingScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ZooStatisticsService {

    private final AnimalRepository animalRepository;
    private final EnclosureRepository enclosureRepository;
    private final FeedingScheduleRepository feedingScheduleRepository;

    public ZooStatisticsService(AnimalRepository animalRepository,
                                EnclosureRepository enclosureRepository,
                                FeedingScheduleRepository feedingScheduleRepository) {
        this.animalRepository = animalRepository;
        this.enclosureRepository = enclosureRepository;
        this.feedingScheduleRepository = feedingScheduleRepository;
    }

    public int getTotalAnimals() {
        return animalRepository.findAll().size();
    }

    public int getSickAnimalsCount() {
        return (int) animalRepository.findAll().stream()
                .filter(animal -> animal.getStatus() != null && animal.getStatus() == HealthStatus.SICK)
                .count();
    }

    public int getTotalEnclosures() {
        return enclosureRepository.findAll().size();
    }

    public long getCompletedFeedings() {
        return feedingScheduleRepository.findAll().stream()
                .filter(FeedingSchedule::isCompleted)
                .count();
    }
}