package com.example.kpo_backend.infrastructure.repository;

import com.example.kpo_backend.domain.model.FeedingSchedule;
import java.util.List;

public interface FeedingScheduleRepository {
    // Сохранить расписание кормления (создать или обновить)
    FeedingSchedule save(FeedingSchedule schedule);

    // Найти расписание кормления по его идентификатору
    FeedingSchedule findById(Long id);

    // Получить список всех расписаний кормления
    List<FeedingSchedule> findAll();

    // Удалить расписание кормления по идентификатору
    void delete(Long id);
}