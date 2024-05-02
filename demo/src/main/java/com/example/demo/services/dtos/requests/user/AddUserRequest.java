package com.example.demo.services.dtos.requests.user;

import com.example.demo.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
    @Length(min = 11,max = 11,message = "Tc numarası 11 hane olmak zorundadır.")
    private String tcNum;

    private UserRole userRole;

    private String firstName;

    private String lastName;

}
