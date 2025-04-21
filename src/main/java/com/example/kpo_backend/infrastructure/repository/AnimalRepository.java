package com.example.kpo_backend.infrastructure.repository;

import com.example.kpo_backend.domain.entity.Animal;
import java.util.List;

public interface AnimalRepository {
    // Сохранить животное (создать или обновить)
    Animal save(Animal animal);

    // Найти животное по его идентификатору
    Animal findById(Long id);

    // Получить список всех животных
    List<Animal> findAll();

    // Удалить животное по идентификатору
    void delete(Long id);
}
