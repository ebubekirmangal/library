package com.example.demo.repositories;

import com.example.demo.entities.Borrow;
import com.example.demo.services.dtos.responses.borrow.GetAllUserWhoBorrowsTheMostBooksResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
    /*@Query(value = "SELECT NEW com.example.demo.services.dtos.responses.borrow.GetAllUserWhoBorrowsTheMostBooksResponse(u.tcNum, u.firstName, u.lastName, COUNT(b) mostBorrow) " +
            "FROM Borrow b " +
            "JOIN b.user u " +
            "GROUP BY u.tcNum, u.firstName, u.lastName " +
            "ORDER BY mostBorrow DESC",nativeQuery = false)
    List<GetAllUserWhoBorrowsTheMostBooksResponse> findTopUserByMostBorrow();

     */
    @Query(value = "Select u.tc_num,u.first_name,u.last_name,Count(b.id) mostBorrow From Borrow b " +
            "Inner Join User u On u.tc_num=b.tc_num " +
            "Group By u.tc_num " +
            "Order By mostBorrow Desc Limit 1",nativeQuery = true)
    List<Borrow> findTopUserByMostBorrow();

}

