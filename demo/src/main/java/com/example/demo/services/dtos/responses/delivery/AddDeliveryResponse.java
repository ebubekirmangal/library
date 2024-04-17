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
public class AddDeliveryResponse {

    private int id;

    private LocalDate receivedDate;

    private String firstName;

    private String lastName;

    private String bookName;

}
