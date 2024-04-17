package com.example.demo.services.dtos.responses.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookResponse {

    private int id;

    private String name;

    private String isbn;

    private int numberOfPage;

    private String categoryName;

    private Boolean isBorrow;

    private int authorId;

    private String authorFirstName;

    private String authorLastName;
}
