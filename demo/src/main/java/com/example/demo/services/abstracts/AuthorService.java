package com.example.demo.services.abstracts;


import com.example.demo.services.dtos.requests.author.AddAuthorRequest;
import com.example.demo.services.dtos.responses.author.AddAuthorResponse;

public interface AuthorService {

    AddAuthorResponse add(AddAuthorRequest request);
}
