package com.example.kpo_backend.presentation.dto;

public class FeedingScheduleRequest {
    private String feedingTime; // формат "HH:mm"
    private String foodType;    // например, "MEAT", "FRUITS"

    // Геттеры/сеттеры
    public String getFeedingTime() { return feedingTime; }
    public void setFeedingTime(String feedingTime) { this.feedingTime = feedingTime; }
    public String getFoodType() { return foodType; }
    public void setFoodType(String foodType) { this.foodType = foodType; }
}