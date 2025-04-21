package com.example.kpo_backend.application.event;


import com.example.kpo_backend.application.event.AnimalMovedEvent;
import com.example.kpo_backend.application.event.FeedingTimeEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DomainEventListener {

    @EventListener
    public void handleAnimalMovedEvent(AnimalMovedEvent event) {
        System.out.println("Получено событие перемещения животного. AnimalID: " + event.getAnimalId());
        // Дополнительная логика обработки события перемещения
    }

    @EventListener
    public void handleFeedingTimeEvent(FeedingTimeEvent event) {
        System.out.println("Пришло время кормления для животного ID: " + event.getAnimalId());
        // Дополнительная логика обработки события кормления
    }
}
