package com.example.demo.services.dtos.requests.user;

import com.example.demo.entities.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    private String tcNum;

    private UserType userType;

    private String firstName;

    private String lastName;

}
