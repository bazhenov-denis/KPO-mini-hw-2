package com.example.kpo_backend.infrastructure.repository.impl;

import com.example.kpo_backend.domain.model.Enclosure;
import com.example.kpo_backend.infrastructure.repository.EnclosureRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryEnclosureRepository implements EnclosureRepository {
    private final Map<Long, Enclosure> enclosureMap = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public Enclosure save(Enclosure enclosure) {
        if (enclosure.getId() == null) {
            enclosure.setId(idCounter++);
        }
        enclosureMap.put(enclosure.getId(), enclosure);
        return enclosure;
    }

    @Override
    public Enclosure findById(Long id) {
        return enclosureMap.get(id);
    }

    @Override
    public List<Enclosure> findAll() {
        return new ArrayList<>(enclosureMap.values());
    }

    @Override
    public void delete(Long id) {
        enclosureMap.remove(id);
    }
}