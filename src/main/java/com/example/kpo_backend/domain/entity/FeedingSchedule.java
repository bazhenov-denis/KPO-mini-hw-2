package com.example.kpo_backend.domain.entity;

import com.example.kpo_backend.domain.valueobject.FoodType;

import java.time.LocalTime;

public class FeedingSchedule {
    private Long id; // уникальный идентификатор
    // Животное, которому предназначено кормление
    private final Animal animal;
    // Время кормления
    private LocalTime feedingTime;
    // Тип пищи для данного кормления
    private FoodType foodType;

    // Флаг выполнения кормления
    private boolean isCompleted;

    // Конструктор
    public FeedingSchedule(Animal animal, LocalTime feedingTime, FoodType foodType) {
        this.animal = animal;
        this.feedingTime = feedingTime;
        this.foodType = foodType;
        this.isCompleted = false;
    }

    // Геттеры
    public Animal getAnimal() {
        return animal;
    }

    public LocalTime getFeedingTime() {
        return feedingTime;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // Метод для изменения расписания кормления:
    // Можно изменить время и тип пищи.
    public void updateSchedule(LocalTime newFeedingTime, FoodType newFoodType) {
        this.feedingTime = newFeedingTime;
        this.foodType = newFoodType;
        // При обновлении расписания сбрасываем флаг выполнения
        this.isCompleted = false;
        System.out.println("Расписание изменено: новое время - " + feedingTime + ", новая еда - " + foodType);
    }

    // Метод для отметки выполнения кормления
    public void markAsCompleted() {
        this.isCompleted = true;
        System.out.println("Кормление для " + animal.getName() + " выполнено.");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFeedingTime(LocalTime feedingTime) {
        this.feedingTime = feedingTime;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
