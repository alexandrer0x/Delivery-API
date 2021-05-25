package dev.alexandrevieira.delivery.services;

import dev.alexandrevieira.delivery.domain.entities.Client;
import dev.alexandrevieira.delivery.domain.repositories.ClientRepository;
import dev.alexandrevieira.delivery.exception.exceptions.BusinessRuleException;
import dev.alexandrevieira.delivery.exception.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static dev.alexandrevieira.delivery.config.Messages.EMAIL_ALREADY_USED;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Optional<Client> find(Long id) {
        return repository.findById(id);
    }

    public List<Client> list() {
        return repository.findAll();
    }

    @Transactional
    public Client create(Client client) {
        if (repository.existsByEmail(client.getEmail()))
            throw new BusinessRuleException(EMAIL_ALREADY_USED);

        client.setId(null);
        return repository.save(client);
    }

    @Transactional
    public Client update(Long id, Client client) {
        if (!repository.existsById(id))
            throw new NotFoundException(Client.class, id);

        Optional<Client> byEmail = repository.findByEmail(client.getEmail());

        if(byEmail.isPresent() && !byEmail.get().getId().equals(id)) {
            throw new BusinessRuleException(EMAIL_ALREADY_USED);
        }

        client.setId(id);
        return repository.save(client);
    }

    @Transactional
    public void delete(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new NotFoundException(Client.class, id);
    }
}
