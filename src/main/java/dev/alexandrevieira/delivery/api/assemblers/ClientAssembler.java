package dev.alexandrevieira.delivery.api.assemblers;

import dev.alexandrevieira.delivery.api.contract.input.ClientInput;
import dev.alexandrevieira.delivery.api.contract.output.ClientOutput;
import dev.alexandrevieira.delivery.domain.entities.Client;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ClientAssembler {
    private ModelMapper mapper;

    public ClientOutput toModel(Client entity) {
        return mapper.map(entity, ClientOutput.class);
    }

    public Client toEntity(ClientInput input) {
        return mapper.map(input, Client.class);
    }
}
