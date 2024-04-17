package com.example.demo.repositories;

import com.example.demo.entities.Punishment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PunishmentManagerRepository extends JpaRepository<Punishment,Integer> {
}
