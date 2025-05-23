package com.example.kpo_backend.presentation.controller;

import com.example.kpo_backend.domain.entity.Enclosure;
import com.example.kpo_backend.infrastructure.repository.EnclosureRepository;
import com.example.kpo_backend.presentation.dto.EnclosureRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enclosures")
public class EnclosureController {
    private final EnclosureRepository enclosureRepository;

    public EnclosureController(EnclosureRepository enclosureRepository) {
        this.enclosureRepository = enclosureRepository;
    }

    @PostMapping
    public ResponseEntity<Enclosure> createEnclosure(@RequestBody EnclosureRequest request) {
        Enclosure enclosure = new Enclosure(
                request.getAllowedType(),
                request.getSize(),
                request.getMaxCapacity()
        );
        enclosureRepository.save(enclosure);
        return ResponseEntity.ok(enclosure);
    }

    @GetMapping
    public ResponseEntity<List<Enclosure>> getAllEnclosures() {
        List<Enclosure> all = enclosureRepository.findAll();
        return ResponseEntity.ok(all);
    }
}
