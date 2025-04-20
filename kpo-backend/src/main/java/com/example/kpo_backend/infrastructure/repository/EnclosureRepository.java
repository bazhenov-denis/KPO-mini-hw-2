package com.example.kpo_backend.infrastructure.repository;

import com.example.kpo_backend.domain.model.Enclosure;
import java.util.List;

public interface EnclosureRepository {
    // Сохранить вольер (создать или обновить)
    Enclosure save(Enclosure enclosure);

    // Найти вольер по его идентификатору
    Enclosure findById(Long id);

    // Получить список всех вольеров
    List<Enclosure> findAll();

    // Удалить вольер по идентификатору
    void delete(Long id);
}