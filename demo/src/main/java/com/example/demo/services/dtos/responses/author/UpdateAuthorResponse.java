package com.example.demo.services.dtos.responses.author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAuthorResponse {

    private int id;

    private String firstName;

    private String lastName;
}
