package com.example.kpo_backend.infrastructure.repository.impl;

import com.example.kpo_backend.domain.model.FeedingSchedule;
import com.example.kpo_backend.infrastructure.repository.FeedingScheduleRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryFeedingScheduleRepository implements FeedingScheduleRepository {
    private final Map<Long, FeedingSchedule> scheduleMap = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public FeedingSchedule save(FeedingSchedule schedule) {
        if (schedule.getId() == null) {
            schedule.setId(idCounter++);
        }
        scheduleMap.put(schedule.getId(), schedule);
        return schedule;
    }

    @Override
    public FeedingSchedule findById(Long id) {
        return scheduleMap.get(id);
    }

    @Override
    public List<FeedingSchedule> findAll() {
        return new ArrayList<>(scheduleMap.values());
    }

    @Override
    public void delete(Long id) {
        scheduleMap.remove(id);
    }
}