package com.example.demo.services.dtos.responses.user;

import com.example.demo.entities.UserType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserResponse {

    private String tcNum;

    //private UserType userType;

    private String firstName;

    private String lastName;

    private List<ListBooksPurshesedSoFar> booksPurshasedSoFar;

}
