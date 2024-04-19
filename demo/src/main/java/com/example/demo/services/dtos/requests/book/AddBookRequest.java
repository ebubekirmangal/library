package com.example.demo.services.dtos.requests.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRequest {
    @NotBlank(message = "Kitap ismi boş olamaz.")
    @Size(min = 2,message="Kitap ismi en az iki haneli olmalı")
    private String name;

    private String isbn;

    private int numberOfPage;

    private int authorId;

    private int categoryId;
}
