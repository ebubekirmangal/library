package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

  User findByTcNum(String tcNum);
  Optional<User> findByEmail(String email);
  boolean existsByEmail(String email); //emaili securityConfiguration kısmında doğrulamak için kullanılır.
}
