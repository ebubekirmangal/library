package com.example.demo.controllers;

import com.example.demo.services.abstracts.BookService;
import com.example.demo.services.dtos.requests.book.AddBookRequest;
import com.example.demo.services.dtos.requests.book.UpdateBookRequest;
import com.example.demo.services.dtos.responses.book.AddBookResponse;
import com.example.demo.services.dtos.responses.book.DeleteBookResponse;
import com.example.demo.services.dtos.responses.book.GetAllBookResponse;
import com.example.demo.services.dtos.responses.book.UpdateBookResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController {

    private BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    @Secured({"ROLE_VISITOR", "ROLE_EMPLOYEE"})
    @ResponseStatus(HttpStatus.CREATED)
    public AddBookResponse add(@RequestBody @Valid AddBookRequest request){
        return bookService.add(request);
    }

    @PutMapping("/update")
    @Secured({"ROLE_VISITOR", "ROLE_EMPLOYEE"})
    public UpdateBookResponse update(@RequestBody UpdateBookRequest request){

        return bookService.update(request);
    }
    @DeleteMapping("/delete/{id}")
    @Secured({"ROLE_VISITOR", "ROLE_EMPLOYEE"})
    public DeleteBookResponse delete(@PathVariable("id") int id ){
        return bookService.delete(id);
    }

    @GetMapping("/getAll")
    @Secured({"ROLE_VISITOR", "ROLE_EMPLOYEE"})
    public List<GetAllBookResponse> getAll(){
        return bookService.getAll();
    }
}
