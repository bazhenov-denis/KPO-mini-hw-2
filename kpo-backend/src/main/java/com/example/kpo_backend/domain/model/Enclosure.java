package com.example.kpo_backend.domain.model;

import com.example.kpo_backend.domain.model.Animal;
import com.example.kpo_backend.domain.valueobject.AnimalType;

import java.util.ArrayList;
import java.util.List;

public class Enclosure {
    private Long id; // уникальный идентификатор
    private final AnimalType allowedType;
    private final double size;
    private final int maxCapacity;

    private final List<Animal> animals = new ArrayList<>();

    public Enclosure(AnimalType allowedType, double size, int maxCapacity) {
        this.allowedType = allowedType;
        this.size = size;
        this.maxCapacity = maxCapacity;
    }

    // Метод, который вызывали
    public AnimalType getAllowedType() {
        return allowedType;
    }

    public boolean addAnimal(Animal animal) {
        if (animals.size() >= maxCapacity) {
            System.out.println("Переполнено!");
            return false;
        }
        animals.add(animal);
        System.out.println("Животное добавлено в вольер: " + animal.getName());
        return true;
    }

    public boolean removeAnimal(Animal animal) {
        boolean removed = animals.remove(animal);
        if (removed) {
            System.out.println("Животное удалено из вольера: " + animal.getName());
        }
        return removed;
    }

    public double getSize() {
        return size;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
