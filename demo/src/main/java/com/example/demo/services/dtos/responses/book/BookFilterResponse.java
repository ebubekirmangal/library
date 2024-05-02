package com.example.demo.services.dtos.responses.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookFilterResponse {

    private String bookName;

    private String firstNameAuthor;

    private String lastNameAuthor;
}
