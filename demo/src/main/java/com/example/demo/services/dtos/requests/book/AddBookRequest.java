package com.example.demo.services.dtos.requests.book;

import jakarta.validation.constraints.Min;
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
    @NotBlank(message = "Kitap İsmi Boş Bırakılamaz!!!")
    @Size(min = 2,message = "Kitap İsmi En Az İki Harften Oluşmalıdır.")
    private String name;

    private String isbn;

    private int authorId;
}
