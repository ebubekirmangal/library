package com.example.demo.repositories;

import com.example.demo.entities.Delivery;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    Optional<Delivery> findById(Optional<Delivery> lastDeliveryOptional);
    //TODO: query yazılacak.
}
