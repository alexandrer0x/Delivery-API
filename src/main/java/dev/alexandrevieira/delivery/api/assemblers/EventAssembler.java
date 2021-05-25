package dev.alexandrevieira.delivery.api.assemblers;

import dev.alexandrevieira.delivery.api.contract.input.EventInput;
import dev.alexandrevieira.delivery.api.contract.output.EventOutput;
import dev.alexandrevieira.delivery.domain.entities.Event;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EventAssembler {
    private ModelMapper modelMapper;

    public Event toEntity(EventInput input) {
        return modelMapper.map(input, Event.class);
    }

    public EventOutput toModel(Event event) {
        return modelMapper.map(event, EventOutput.class);
    }
}
