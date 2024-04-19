package com.example.demo.services.abstracts;

import com.example.demo.entities.Book;
import com.example.demo.services.dtos.requests.book.AddBookRequest;
import com.example.demo.services.dtos.requests.book.UpdateBookRequest;
import com.example.demo.services.dtos.responses.book.*;

import java.util.List;

public interface BookService {

    AddBookResponse add(AddBookRequest request);

    UpdateBookResponse update(UpdateBookRequest request);

    DeleteBookResponse delete(int id);

    List<GetAllBookResponse> getAll();

    GetByIdBookResponse getById(int id);

    Book findById(int id);

}
