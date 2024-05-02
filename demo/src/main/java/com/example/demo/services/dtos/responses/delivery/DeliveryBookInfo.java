package com.example.demo.services.dtos.responses.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryBookInfo {

    private String bookName;

    private LocalDate deadLine;

    private int delayDate;

    private LocalDate receivedDate;

    private LocalDate pickUpDate;

    private Double totalFee;

    private String message;
}
