package com.example.kpo_backend.presentation.dto;

public class AnimalRequest {
    private String name;
    private String species;       // например, "LION"
    private String birthDate;     // в формате "yyyy-MM-dd"
    private String gender;        // "MALE" или "FEMALE"
    private String favoriteFood;  // например, "MEAT"
    private String status;        // "HEALTHY" или "SICK"

    // Геттеры и сеттеры
    // ...
    // (генерация через Lombok или вручную)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getFavoriteFood() { return favoriteFood; }
    public void setFavoriteFood(String favoriteFood) { this.favoriteFood = favoriteFood; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}