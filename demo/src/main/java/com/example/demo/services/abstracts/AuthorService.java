package com.example.demo.services.abstracts;


import com.example.demo.entities.Author;
import com.example.demo.services.dtos.requests.author.AddAuthorRequest;
import com.example.demo.services.dtos.requests.author.UpdateAuthorRequest;
import com.example.demo.services.dtos.responses.author.AddAuthorResponse;
import com.example.demo.services.dtos.responses.author.DeleteAuthorResponse;
import com.example.demo.services.dtos.responses.author.GetAllAuthorResponse;
import com.example.demo.services.dtos.responses.author.UpdateAuthorResponse;

import java.util.List;

public interface AuthorService {

    AddAuthorResponse add(AddAuthorRequest request);

    UpdateAuthorResponse update(UpdateAuthorRequest request);

    DeleteAuthorResponse delete(int id);

    List<GetAllAuthorResponse> getAll();

    Author findById(int id);
}
