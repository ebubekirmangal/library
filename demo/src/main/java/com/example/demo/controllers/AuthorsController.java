package com.example.demo.controllers;

import com.example.demo.services.abstracts.AuthorService;
import com.example.demo.services.dtos.requests.author.AddAuthorRequest;
import com.example.demo.services.dtos.requests.author.UpdateAuthorRequest;
import com.example.demo.services.dtos.responses.author.AddAuthorResponse;
import com.example.demo.services.dtos.responses.author.DeleteAuthorResponse;
import com.example.demo.services.dtos.responses.author.GetAllAuthorResponse;
import com.example.demo.services.dtos.responses.author.UpdateAuthorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/control/author")
public class AuthorsController {

    private AuthorService authorService;

    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AddAuthorResponse add(@RequestBody AddAuthorRequest request){
        return  authorService.add(request);
    }

    @PutMapping("/update")
    public UpdateAuthorResponse update(@RequestBody UpdateAuthorRequest request){

        return authorService.update(request);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteAuthorResponse delete(@PathVariable("id") int id){
        return authorService.delete(id);
    }

    @GetMapping("/GetAll")
    public List<GetAllAuthorResponse> getAll(){
        return authorService.getAll();
    }
}
