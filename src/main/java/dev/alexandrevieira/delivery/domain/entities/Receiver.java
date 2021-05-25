package dev.alexandrevieira.delivery.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Receiver {
    @Column(name = "receiver_name", nullable = false, length = 60)
    private String name;

    @Column(name = "receiver_number", nullable = false, length = 10)
    private String number;

    @Column(name = "receiver_street", nullable = false, length = 60)
    private String street;

    @Column(name = "receiver_complement", length = 60)
    private String complement;

    @Column(name = "receiver_district", nullable = false, length = 30)
    private String district;
}
