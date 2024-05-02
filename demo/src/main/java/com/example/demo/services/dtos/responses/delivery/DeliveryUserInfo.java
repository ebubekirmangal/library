package com.example.demo.services.dtos.responses.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryUserInfo {

    private String tcNum;

    private String firstName;

    private String lastName;
}
