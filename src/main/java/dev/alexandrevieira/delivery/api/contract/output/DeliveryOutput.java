package dev.alexandrevieira.delivery.api.contract.output;

import dev.alexandrevieira.delivery.domain.enums.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class DeliveryOutput {
    private Long id;
    private ClientResponse client;
    private ReceiverResponse receiver;
    private BigDecimal fee;
    private List<EventOutput> events;
    private DeliveryStatus status;
    private OffsetDateTime requestDate;
    private OffsetDateTime deliveryDate;

    @Getter
    @Setter
    public static class ReceiverResponse {
        private String name;
        private String number;
        private String street;
        private String complement;
        private String district;
    }

    @Getter
    @Setter
    public static class ClientResponse {
        private Long id;
        private String name;
    }
}
