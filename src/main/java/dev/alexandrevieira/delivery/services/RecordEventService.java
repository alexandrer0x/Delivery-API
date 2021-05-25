package dev.alexandrevieira.delivery.services;

import dev.alexandrevieira.delivery.domain.entities.Delivery;
import dev.alexandrevieira.delivery.domain.entities.Event;
import dev.alexandrevieira.delivery.domain.repositories.DeliveryRepository;
import dev.alexandrevieira.delivery.exception.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RecordEventService {
    private DeliveryRepository deliveryRepository;

    @Transactional
    public Event record(Long deliveryId, String description) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new NotFoundException(Delivery.class, deliveryId));

        return delivery.addEvent(description);
    }
}
