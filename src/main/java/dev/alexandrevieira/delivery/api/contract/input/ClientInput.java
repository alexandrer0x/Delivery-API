package dev.alexandrevieira.delivery.api.contract.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ClientInput {
    @NotBlank
    @Email
    @Size(max = 60)
    private String email;

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 11, min = 11)
    private String phone;
}
