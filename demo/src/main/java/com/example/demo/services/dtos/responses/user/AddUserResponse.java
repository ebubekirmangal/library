package com.example.demo.services.dtos.responses.user;

import com.example.demo.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserResponse {

    private String tcNum;

    private Role role;

    private String firstName;

    private String lastName;

}
