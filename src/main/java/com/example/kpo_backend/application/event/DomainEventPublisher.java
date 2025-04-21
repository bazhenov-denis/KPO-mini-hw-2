package com.example.kpo_backend.application.event;

import com.example.kpo_backend.application.event.AnimalMovedEvent;
import com.example.kpo_backend.application.event.FeedingTimeEvent;

public interface DomainEventPublisher {
    void publish(AnimalMovedEvent event);
    void publish(FeedingTimeEvent event);

    // или можно сделать обобщенный метод publish(Object event),
    // если хотите общий подход к событиям
}


