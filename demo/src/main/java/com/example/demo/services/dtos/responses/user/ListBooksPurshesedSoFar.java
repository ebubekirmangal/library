package com.example.demo.services.dtos.responses.user;

import com.example.demo.entities.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListBooksPurshesedSoFar {

    private String bookName;

    private BookStatus bookStatus;

    private LocalDate deadLine;

    private Double totalFee;
}
