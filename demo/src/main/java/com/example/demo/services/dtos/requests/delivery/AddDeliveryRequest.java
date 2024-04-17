package com.example.demo.services.dtos.requests.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddDeliveryRequest {


    private int borrowId;

    private LocalDate receivedDate;

}
