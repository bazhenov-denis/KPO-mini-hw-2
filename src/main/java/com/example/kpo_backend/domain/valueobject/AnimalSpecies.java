package com.example.kpo_backend.domain.valueobject;

public enum AnimalSpecies {
    LION(AnimalType.PREDATOR),
    TIGER(AnimalType.PREDATOR),
    WOLF(AnimalType.PREDATOR),

    ELEPHANT(AnimalType.HERBIVORE),
    ZEBRA(AnimalType.HERBIVORE),

    PARROT(AnimalType.BIRD),
    EAGLE(AnimalType.BIRD),

    SNAKE(AnimalType.REPTILES),
    LIZARD(AnimalType.REPTILES),

    GOLDFISH(AnimalType.FISH),
    SHARK(AnimalType.FISH),

    ANT(AnimalType.INSECTS),
    BUTTERFLY(AnimalType.INSECTS);

    private final AnimalType type;

    AnimalSpecies(AnimalType type) {
        this.type = type;
    }

    public AnimalType getType() {
        return type;
    }
}
