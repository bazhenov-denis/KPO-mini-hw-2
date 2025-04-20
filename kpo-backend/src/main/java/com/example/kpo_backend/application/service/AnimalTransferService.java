package com.example.kpo_backend.application.service;

import com.example.kpo_backend.application.event.AnimalMovedEvent;
import com.example.kpo_backend.application.event.DomainEventPublisher;
import com.example.kpo_backend.domain.entity.Animal;
import com.example.kpo_backend.domain.entity.Enclosure;
import com.example.kpo_backend.infrastructure.repository.AnimalRepository;
import com.example.kpo_backend.infrastructure.repository.EnclosureRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnimalTransferService {

    private final AnimalRepository animalRepository;
    private final EnclosureRepository enclosureRepository;
    private final DomainEventPublisher eventPublisher;

    public AnimalTransferService(AnimalRepository animalRepository,
                                 EnclosureRepository enclosureRepository,
                                 DomainEventPublisher eventPublisher) {
        this.animalRepository = animalRepository;
        this.enclosureRepository = enclosureRepository;
        this.eventPublisher = eventPublisher;
    }

    public void moveAnimal(Long animalId, Long newEnclosureId) {
        Animal animal = animalRepository.findById(animalId);
        Enclosure newEnclosure = enclosureRepository.findById(newEnclosureId);

        if (animal == null || newEnclosure == null) {
            throw new IllegalArgumentException("Животное или вольер не найдены");
        }

        if (!newEnclosure.getAllowedType().equals(animal.getAnimalSpecies().getType())) {
            throw new IllegalStateException("Тип вольера не соответствует типу животного");
        }

        if (newEnclosure.getAnimals().size() >= newEnclosure.getMaxCapacity()) {
            throw new IllegalStateException("Вольер переполнен");
        }

        Enclosure oldEnclosure = animal.getCurrentEnclosure();
        if (oldEnclosure != null) {
            oldEnclosure.removeAnimal(animal);
            enclosureRepository.save(oldEnclosure);
        }

        newEnclosure.addAnimal(animal);
        enclosureRepository.save(newEnclosure);

        animal.setCurrentEnclosure(newEnclosure);
        animalRepository.save(animal);

        AnimalMovedEvent event = new AnimalMovedEvent(
                animal.getId(),
                oldEnclosure != null ? oldEnclosure.getId() : null,
                newEnclosure.getId(),
                LocalDateTime.now()
        );
        eventPublisher.publish(event);
    }
}
