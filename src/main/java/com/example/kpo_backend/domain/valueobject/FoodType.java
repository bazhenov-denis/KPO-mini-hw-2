package com.example.kpo_backend.domain.valueobject;

public enum FoodType {
    MEAT,          // мясо — для хищников
    PLANTS,        // растения, трава — для травоядных
    INSECTS,       // насекомые — для птиц, ящериц и т.п.
    FISH,          // рыба — для рыб и хищников, например, пингвинов
    FRUITS,        // фрукты — для обезьян, птиц и др.
    SEEDS,         // семена — для птиц
    VEGETABLES,    // овощи
    MIXED_FEED,    // комбикорм
    LIVE_PREY,     // живая добыча — например, мыши для змей
    OTHER          // если не попадает в основные категории
}