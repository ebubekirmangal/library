package com.example.demo.services.dtos.responses.user;

import com.example.demo.entities.Borrow;
import com.example.demo.entities.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserResponse {

    private String tcNum;

    private UserType userType;

    private String firstName;

    private String lastName;

    private List<Borrow> borrows;
    //TODO:bakılacak
}
