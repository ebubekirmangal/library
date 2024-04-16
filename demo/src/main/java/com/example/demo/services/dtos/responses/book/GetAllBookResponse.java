package com.example.demo.services.dtos.responses.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBookResponse {

    private int id;

    private String name;

    private String isbn;

    private int authorId;

    private String authorFirstName;

    private String authorLastName;
}
