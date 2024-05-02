package com.example.demo.services.dtos.responses.delivery;

import com.example.demo.entities.BookStatus;
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
public class DeleteDeliveryResponse {

    private int id;

    private String tcNum;

    private String firstName;

    private String lastName;

    private List<String> bookNames;

    private LocalDate deadLine;

    private int delayDate;

    private LocalDate receivedDate;

    private LocalDate pickUpDate;

    private Double totalFee;

    private String message;

//    private  List<DeliveryUserInfo> userInfos;
//
//    private List<DeliveryBookInfo> deliveryInfos;


}
