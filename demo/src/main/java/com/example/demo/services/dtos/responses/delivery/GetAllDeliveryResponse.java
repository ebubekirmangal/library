package com.example.demo.services.dtos.responses.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllDeliveryResponse {

    private int id;

    private String tcNum;

    private String firstName;

    private String lastName;

    private String bookName;

    private LocalDate deadLine;

    private int delayDate;

    private LocalDate receivedDate;

    private LocalDate pickUpDate;

    private Double totalFee;

    private String message;

}
