package com.example.kpo_backend.domain.entity;

import com.example.kpo_backend.domain.valueobject.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;

public class Animal {
    private Long id; // уникальный идентификатор
    private AnimalSpecies animalSpecies;
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private FoodType favoriteFood;
    private HealthStatus status;

    // Важно: это поле нужно, если вы хотите хранить текущий вольер
    @JsonBackReference
    private Enclosure currentEnclosure;

    public Animal(String name,
                  AnimalSpecies species,
                  LocalDate birthDate,
                  Gender gender,
                  FoodType favoriteFood,
                  HealthStatus status) {
        this.name = name;
        this.animalSpecies = species;
        this.birthDate = birthDate;
        this.gender = gender;
        this.favoriteFood = favoriteFood;
        this.status = status;
    }

    // Метод "переместить в другой вольер"
    public void moveToEnclosure(Enclosure newEnclosure) {
        if (newEnclosure.getAllowedType() != animalSpecies.getType()) {
            System.out.println("Нельзя переместить " + name + ": тип животного не соответствует вольеру.");
            return;
        }

        // Пробуем добавить в новый вольер
        boolean added = newEnclosure.addAnimal(this);
        if (!added) {
            System.out.println("Не удалось добавить " + name + " в новый вольер (переполнен?).");
            return;
        }

        // Удаляем из старого вольера, если есть
        if (currentEnclosure != null) {
            currentEnclosure.removeAnimal(this);
        }

        // Обновляем поле
        currentEnclosure = newEnclosure;
        System.out.println(name + " перемещен в новый вольер.");
    }

    // Пример метода "кормить"
    public void feed(FoodType food) {
        if (status == HealthStatus.SICK) {
            System.out.println(name + " болен и не хочет есть.");
            return;
        }

        if (food == favoriteFood) {
            System.out.println(name + " съел любимую еду: " + food);
        } else {
            System.out.println(name + " съел " + food + ", но предпочитает " + favoriteFood);
        }
    }

    // Пример метода "лечить"
    public void heal() {
        if (status == HealthStatus.HEALTHY) {
            System.out.println(name + " уже здоров.");
        } else {
            status = HealthStatus.HEALTHY;
            System.out.println(name + " был вылечен!");
        }
    }

    // Геттеры, если нужны
    public String getName() {
        return name;
    }

    public AnimalSpecies getAnimalSpecies() {
        return animalSpecies;
    }

    public Enclosure getCurrentEnclosure() {
        return currentEnclosure;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public FoodType getFavoriteFood() {
        return favoriteFood;
    }

    public HealthStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAnimalSpecies(AnimalSpecies animalSpecies) {
        this.animalSpecies = animalSpecies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setFavoriteFood(FoodType favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public void setStatus(HealthStatus status) {
        this.status = status;
    }

    public void setCurrentEnclosure(Enclosure currentEnclosure) {
        this.currentEnclosure = currentEnclosure;
    }
}
