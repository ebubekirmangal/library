package com.example.demo.services.dtos.requests.author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddAuthorRequest {

    private String firstName;

    private String lastName;
}
