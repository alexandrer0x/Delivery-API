package dev.alexandrevieira.delivery.api.controllers;

import dev.alexandrevieira.delivery.api.assemblers.EventAssembler;
import dev.alexandrevieira.delivery.api.contract.input.EventInput;
import dev.alexandrevieira.delivery.api.contract.output.EventOutput;
import dev.alexandrevieira.delivery.domain.entities.Delivery;
import dev.alexandrevieira.delivery.domain.entities.Event;
import dev.alexandrevieira.delivery.exception.exceptions.NotFoundException;
import dev.alexandrevieira.delivery.services.DeliveryService;
import dev.alexandrevieira.delivery.services.RecordEventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/deliveries/{deliveryId}/events")
public class EventController {
    private RecordEventService eventService;
    private EventAssembler assembler;
    private DeliveryService deliveryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventOutput record(@PathVariable Long deliveryId,
                                              @Valid @RequestBody EventInput input) {

        Event event = eventService.record(deliveryId, input.getDescription());
        return assembler.toModel(event);
    }

    @GetMapping
    public List<EventOutput> list(@PathVariable Long deliveryId) {
        Delivery delivery = deliveryService.find(deliveryId)
                .orElseThrow(() -> new NotFoundException(Delivery.class, deliveryId));

        return delivery.getEvents()
                .stream().map(assembler::toModel).collect(Collectors.toList());
    }
}
