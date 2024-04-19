package com.example.demo.controllers;

import com.example.demo.services.abstracts.BookService;
import com.example.demo.services.dtos.requests.book.AddBookRequest;
import com.example.demo.services.dtos.requests.book.UpdateBookRequest;
import com.example.demo.services.dtos.responses.book.AddBookResponse;
import com.example.demo.services.dtos.responses.book.DeleteBookResponse;
import com.example.demo.services.dtos.responses.book.GetAllBookResponse;
import com.example.demo.services.dtos.responses.book.UpdateBookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BooksController {

    private BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AddBookResponse add(@RequestBody AddBookRequest request){
        return bookService.add(request);
    }

    @PutMapping("/update")
    public UpdateBookResponse update(@RequestBody UpdateBookRequest request){

        return bookService.update(request);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteBookResponse delete(@PathVariable("id") int id ){
        return bookService.delete(id);
    }

    @GetMapping("/GetAll")
    public List<GetAllBookResponse> getAll(){
        return bookService.getAll();
    }
}
