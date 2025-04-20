package com.example.kpo_backend.presentation.dto;

public class ZooStatsResponse {
    private int totalAnimals;
    private int sickAnimals;
    private int totalEnclosures;
    private long completedFeedings;

    public ZooStatsResponse(int totalAnimals, int sickAnimals, int totalEnclosures, long completedFeedings) {
        this.totalAnimals = totalAnimals;
        this.sickAnimals = sickAnimals;
        this.totalEnclosures = totalEnclosures;
        this.completedFeedings = completedFeedings;
    }
    // Геттеры и сеттеры
    public int getTotalAnimals() { return totalAnimals; }
    public void setTotalAnimals(int totalAnimals) { this.totalAnimals = totalAnimals; }
    public int getSickAnimals() { return sickAnimals; }
    public void setSickAnimals(int sickAnimals) { this.sickAnimals = sickAnimals; }
    public int getTotalEnclosures() { return totalEnclosures; }
    public void setTotalEnclosures(int totalEnclosures) { this.totalEnclosures = totalEnclosures; }
    public long getCompletedFeedings() { return completedFeedings; }
    public void setCompletedFeedings(long completedFeedings) { this.completedFeedings = completedFeedings; }
}