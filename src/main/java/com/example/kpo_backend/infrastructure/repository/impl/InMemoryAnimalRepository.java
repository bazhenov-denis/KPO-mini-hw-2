package com.example.kpo_backend.infrastructure.repository.impl;

import com.example.kpo_backend.domain.entity.Animal;
import com.example.kpo_backend.infrastructure.repository.AnimalRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryAnimalRepository implements AnimalRepository {
    private final Map<Long, Animal> animalMap = new HashMap<>();
    private Long idCounter = 1L; // счетчик для уникальных id

    @Override
    public Animal save(Animal animal) {
        // если id еще не задан, присваиваем новый
        if (animal.getId() == null) {
            animal.setId(idCounter++);
        }
        animalMap.put(animal.getId(), animal);
        return animal;
    }

    @Override
    public Animal findById(Long id) {
        return animalMap.get(id);
    }

    @Override
    public List<Animal> findAll() {
        return new ArrayList<>(animalMap.values());
    }

    @Override
    public void delete(Long id) {
        animalMap.remove(id);
    }
}
