package dev.alexandrevieira.delivery.services;

import dev.alexandrevieira.delivery.config.Messages;
import dev.alexandrevieira.delivery.domain.entities.Client;
import dev.alexandrevieira.delivery.domain.entities.Delivery;
import dev.alexandrevieira.delivery.domain.enums.DeliveryStatus;
import dev.alexandrevieira.delivery.domain.repositories.ClientRepository;
import dev.alexandrevieira.delivery.domain.repositories.DeliveryRepository;
import dev.alexandrevieira.delivery.exception.exceptions.BusinessRuleException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RequestDeliveryService {
    private ClientRepository clientRepository;
    private DeliveryRepository deliveryRepository;

    @Transactional
    public Delivery requestDelivery(Delivery delivery){
        long clientId = delivery.getClient().getId();

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new BusinessRuleException(Messages.CLIENT_NOT_FOUND));

        delivery.setClient(client);
        delivery.updateStatus(DeliveryStatus.PENDING);

        return deliveryRepository.save(delivery);
    }

}
