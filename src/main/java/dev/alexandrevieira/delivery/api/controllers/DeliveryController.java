package dev.alexandrevieira.delivery.api.controllers;

import dev.alexandrevieira.delivery.api.assemblers.DeliveryAssembler;
import dev.alexandrevieira.delivery.api.contract.input.DeliveryInput;
import dev.alexandrevieira.delivery.api.contract.input.StatusInput;
import dev.alexandrevieira.delivery.api.contract.output.DeliveryOutput;
import dev.alexandrevieira.delivery.domain.entities.Delivery;
import dev.alexandrevieira.delivery.domain.enums.DeliveryStatus;
import dev.alexandrevieira.delivery.exception.exceptions.NotFoundException;
import dev.alexandrevieira.delivery.services.DeliveryService;
import dev.alexandrevieira.delivery.services.RequestDeliveryService;
import dev.alexandrevieira.delivery.services.UpdateDeliveryStatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/deliveries")
public class DeliveryController {
    private DeliveryService service;
    private RequestDeliveryService requestDeliveryService;
    private UpdateDeliveryStatusService statusService;
    private DeliveryAssembler assembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DeliveryOutput> requestDelivery(@Valid @RequestBody DeliveryInput input) {
        Delivery newDelivery = assembler.toEntity(input);
        Delivery saved = requestDeliveryService.requestDelivery(newDelivery);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(assembler.toModel(saved));
    }

    @GetMapping
    public List<DeliveryOutput> list() {
        return service.list().stream()
                .map(delivery -> assembler.toModel(delivery))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public DeliveryOutput find(@PathVariable Long id) {
        return service.find(id)
                .map(delivery -> assembler.toModel(delivery))
                .orElseThrow(() -> new NotFoundException(Delivery.class, id));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PatchMapping(path = "/{id}")
    public DeliveryOutput updateStatus(@PathVariable Long id, @RequestBody StatusInput input) {
        DeliveryStatus status = DeliveryStatus.valueOf(input.getStatus());
        Delivery delivery =  statusService.updateStatus(id, status);
        return  assembler.toModel(delivery);
    }
}
