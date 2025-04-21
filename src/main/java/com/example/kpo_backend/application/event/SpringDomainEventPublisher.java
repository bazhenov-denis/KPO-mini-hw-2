package com.example.kpo_backend.application.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringDomainEventPublisher implements DomainEventPublisher {

    private final ApplicationEventPublisher publisher;

    public SpringDomainEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(AnimalMovedEvent event) {
        publisher.publishEvent(event);
    }

    @Override
    public void publish(FeedingTimeEvent event) {
        publisher.publishEvent(event);
    }
}
