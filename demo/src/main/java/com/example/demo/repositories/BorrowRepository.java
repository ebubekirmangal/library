package com.example.demo.repositories;

import com.example.demo.entities.Borrow;
import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow,Integer> {

}
