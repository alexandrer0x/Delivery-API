package dev.alexandrevieira.delivery.api.contract.output;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class EventOutput {
    private Long id;
    private String description;
    private OffsetDateTime recordDate;
}
