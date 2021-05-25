package dev.alexandrevieira.delivery.services;

import dev.alexandrevieira.delivery.domain.entities.Delivery;
import dev.alexandrevieira.delivery.domain.repositories.DeliveryRepository;
import dev.alexandrevieira.delivery.exception.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {
    @Autowired
    private DeliveryRepository repository;

    public List<Delivery> list() {
        return repository.findAll();
    }

    public Optional<Delivery> find(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void delete(Long id) {
        if(repository.existsById(id))
            repository.deleteById(id);
        else
            throw new NotFoundException(Delivery.class, id);
    }
}
