package com.example.kpo_backend.presentation.controller;

import com.example.kpo_backend.domain.valueobject.AnimalSpecies;
import com.example.kpo_backend.domain.valueobject.FoodType;
import com.example.kpo_backend.presentation.dto.SpeciesResponse;
import com.example.kpo_backend.presentation.dto.FoodTypesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/dictionaries")
@CrossOrigin(origins = "http://localhost:5173")  // если фронт на другом порту
@RequiredArgsConstructor
public class DictionaryController {

    /** Возвращает { "species": ["LION","TIGER",…] } */
    @GetMapping("/animal-species")
    public ResponseEntity<SpeciesResponse> getAnimalSpecies() {
        List<String> list = Arrays.stream(AnimalSpecies.values())
                .map(Enum::name)
                .toList();
        return ResponseEntity.ok(new SpeciesResponse(list));
    }

    /** Возвращает { "foodTypes": ["MEAT","PLANTS",…] } */
    @GetMapping("/food-types")
    public ResponseEntity<FoodTypesResponse> getFoodTypes() {
        List<String> list = Arrays.stream(FoodType.values())
                .map(Enum::name)
                .toList();
        return ResponseEntity.ok(new FoodTypesResponse(list));
    }
}
