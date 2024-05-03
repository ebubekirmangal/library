package com.example.demo.services.dtos.requests.user;

import com.example.demo.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {

    private String tcNum;

    private Role role;

    private String firstName;

    private String lastName;

    private String email;
    private String password;

}
