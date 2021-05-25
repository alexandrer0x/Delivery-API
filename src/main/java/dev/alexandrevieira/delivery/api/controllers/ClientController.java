package dev.alexandrevieira.delivery.api.controllers;

import dev.alexandrevieira.delivery.api.assemblers.ClientAssembler;
import dev.alexandrevieira.delivery.api.contract.input.ClientInput;
import dev.alexandrevieira.delivery.api.contract.output.ClientOutput;
import dev.alexandrevieira.delivery.domain.entities.Client;
import dev.alexandrevieira.delivery.exception.exceptions.NotFoundException;
import dev.alexandrevieira.delivery.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/clients")
public class ClientController {
    @Autowired
    private ClientService service;

    @Autowired
    private ClientAssembler assembler;

    @GetMapping
    public List<ClientOutput> list() {
        return service.list()
                .stream().map(x -> assembler.toModel(x)).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public ClientOutput find(@PathVariable Long id) {
        return service.find(id)
                .map(assembler::toModel)
                .orElseThrow(() -> new NotFoundException(Client.class, id));
    }

    @PostMapping
    public ResponseEntity<ClientOutput> create(@Valid @RequestBody ClientInput input) {
        Client client = service.create(assembler.toEntity(input));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).body(assembler.toModel(client));
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody ClientInput input) {
        Client client = assembler.toEntity(input);
        service.update(id, client);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
