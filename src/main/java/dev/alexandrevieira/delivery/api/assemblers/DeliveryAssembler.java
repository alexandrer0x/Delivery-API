package dev.alexandrevieira.delivery.api.assemblers;

import dev.alexandrevieira.delivery.api.contract.input.DeliveryInput;
import dev.alexandrevieira.delivery.api.contract.output.DeliveryOutput;
import dev.alexandrevieira.delivery.domain.entities.Delivery;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DeliveryAssembler {
    private ModelMapper mapper;

    public DeliveryOutput toModel(Delivery entity) {
        return mapper.map(entity, DeliveryOutput.class);
    }

    public Delivery toEntity(DeliveryInput input) {
        return mapper.map(input, Delivery.class);
    }
}
