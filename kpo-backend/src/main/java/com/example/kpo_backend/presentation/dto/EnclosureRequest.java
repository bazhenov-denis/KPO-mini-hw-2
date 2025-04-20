package com.example.kpo_backend.presentation.dto;

import com.example.kpo_backend.domain.valueobject.AnimalType;

public class EnclosureRequest {
    private AnimalType allowedType;
    private double size;
    private int maxCapacity;

    public AnimalType getAllowedType() {
        return allowedType;
    }

    public void setAllowedType(AnimalType allowedType) {
        this.allowedType = allowedType;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
