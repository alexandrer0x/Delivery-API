package dev.alexandrevieira.delivery.domain.entities;

import dev.alexandrevieira.delivery.common.EventDescriptions;
import dev.alexandrevieira.delivery.config.Messages;
import dev.alexandrevieira.delivery.domain.enums.DeliveryStatus;
import dev.alexandrevieira.delivery.exception.exceptions.BusinessRuleException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @Setter
    private Client client;

    @Embedded
    @Setter
    private Receiver receiver;

    @Column(nullable = false, precision = 10, scale = 2)
    @Setter
    private BigDecimal fee;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;

    @Column(nullable = false)
    @Setter
    private OffsetDateTime requestDate;

    @Setter
    private OffsetDateTime deliveryDate;

    public Event addEvent(String description) {
        Event event = new Event();
        event.setDescription(description);
        event.setRecordDate(OffsetDateTime.now());
        event.setDelivery(this);
        this.events.add(event);
        return event;
    }

    public boolean canBeFinished() {
        return this.status.equals(DeliveryStatus.SHIPPED);
    }

    public boolean canBeCanceled() {
        return !this.status.equals(DeliveryStatus.DELIVERED);
    }

    public boolean canBeShipped() {
        return this.status.equals(DeliveryStatus.PENDING);
    }

    public void updateStatus(DeliveryStatus status) {
        switch (status) {
            case CANCELED:
                this.cancel();
                break;

            case PENDING:
                this.initialize();
                break;

            case SHIPPED:
                this.ship();
                break;

            case DELIVERED:
                this.finish();
                break;

            default:
                throw new BusinessRuleException(Messages.INVALID_STATUS);
        }
    }

    private void initialize() {
        if(this.status != null)
            throw new BusinessRuleException(Messages.CANNOT_CHANGE_TO_PENDING);

        Event event = this.addEvent(EventDescriptions.DELIVERY_REQUESTED);
        this.setRequestDate(event.getRecordDate());
        this.status = DeliveryStatus.PENDING;
    }

    private void finish() {
        if (!canBeFinished())
            throw new BusinessRuleException(Messages.CANNOT_BE_FINISHED);

        Event event = this.addEvent(EventDescriptions.DELIVERED_TO_RECEIVER);
        this.setDeliveryDate(event.getRecordDate());
        this.status = DeliveryStatus.DELIVERED;
    }

    private void cancel() {
        if (!this.canBeCanceled())
            throw new BusinessRuleException(Messages.CANNOT_BE_CANCELED);

        this.addEvent(EventDescriptions.DELIVERY_CANCELED);
        this.status = DeliveryStatus.CANCELED;
    }

    private void ship() {
        if(!this.canBeShipped())
            throw new BusinessRuleException(Messages.CANNOT_BE_SHIPPED);

        this.addEvent(EventDescriptions.DELIVERY_SHIPPED);
        this.status = DeliveryStatus.SHIPPED;
    }
}
