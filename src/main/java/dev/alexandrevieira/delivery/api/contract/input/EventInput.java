package dev.alexandrevieira.delivery.api.contract.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EventInput {
    @NotBlank
    private String description;
}
