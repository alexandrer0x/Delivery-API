package dev.alexandrevieira.delivery.api.contract.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientOutput {
    private Long id;
    private String email;
    private String name;
    private String phone;
}
