package dev.alexandrevieira.delivery.api.contract.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
public class DeliveryInput {
    @Valid
    @NotNull
    private ClientIdInput client;

    @Valid
    @NotNull
    private ReceiverInput receiver;

    @NotNull
    @Min(0)
    private BigDecimal fee;


    @Getter
    @Setter
    private static class ClientIdInput {
        @NotNull
        private Long id;
    }

    @Getter
    @Setter
    private static class ReceiverInput {
        @Size(max = 60)
        @NotBlank
        private String name;

        @Size(max = 10)
        @NotBlank
        private String number;

        @Size(max = 60)
        @NotBlank
        private String street;

        @Size(max = 60)
        private String complement;

        @Size(max = 30)
        @NotBlank
        private String district;
    }
}
