package com.example.demo.services.dtos.requests.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookRequest {

    private int id;

    private String name;

    private String isbn;

    private int numberOfPage;

    private int authorId;

    private int categoryId;


}
