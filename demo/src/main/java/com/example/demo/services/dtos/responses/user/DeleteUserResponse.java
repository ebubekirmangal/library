package com.example.demo.services.dtos.responses.user;

import com.example.demo.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserResponse {

    private String tcNum;

    private UserRole userRole;

    private String firstName;

    private String lastName;

}
