package dev.alexandrevieira.delivery.domain.repositories;

import dev.alexandrevieira.delivery.domain.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
