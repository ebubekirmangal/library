package com.example.demo.services.dtos.responses.borrow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBorrowResponse {

    private int id;

    private String firstName;

    private String lastName;

    private List<String> bookNames;

    private LocalDateTime pickUpDate;
}
