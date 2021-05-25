package dev.alexandrevieira.delivery.services;

import dev.alexandrevieira.delivery.config.Messages;
import dev.alexandrevieira.delivery.domain.entities.Delivery;
import dev.alexandrevieira.delivery.domain.enums.DeliveryStatus;
import dev.alexandrevieira.delivery.domain.repositories.DeliveryRepository;
import dev.alexandrevieira.delivery.exception.exceptions.BusinessRuleException;
import dev.alexandrevieira.delivery.exception.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UpdateDeliveryStatusService {
    private DeliveryRepository repository;

    @Transactional
    public Delivery updateStatus(Long deliveryId, DeliveryStatus status) {
        Delivery delivery = repository.findById(deliveryId)
                .orElseThrow(() -> new NotFoundException(Delivery.class, deliveryId));

        if(status.equals(delivery.getStatus()))
            throw new BusinessRuleException(Messages.SAME_STATUS);

        delivery.updateStatus(status);
        return repository.save(delivery);
    }
}
