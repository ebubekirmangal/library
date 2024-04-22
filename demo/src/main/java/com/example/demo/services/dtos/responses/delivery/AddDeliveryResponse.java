package com.example.demo.services.dtos.responses.delivery;

import com.example.demo.entities.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddDeliveryResponse {

    private int id;

    private String tcNum;

    private LocalDate deadLine;

    private long delayDate;

    private LocalDate receivedDate;

    private LocalDate pickUpDate;

    private Double totalFee;

    private String message;

    private BookStatus bookStatus;

}
