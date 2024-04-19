package com.example.demo.services.dtos.responses.borrow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddBorrowResponse {

    private int id;

    private String firstName;

    private String lastName;

    private String bookName;

    private LocalDate pickUpDate;
    private Double totalFee;
    private Boolean isBorrow;
}
