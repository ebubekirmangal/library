package com.example.demo.services.concretes;

import com.example.demo.entities.Author;
import com.example.demo.mappers.AuthorMapper;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.services.abstracts.AuthorService;
import com.example.demo.services.dtos.requests.author.AddAuthorRequest;
import com.example.demo.services.dtos.responses.author.AddAuthorResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AddAuthorResponse add(AddAuthorRequest request) {

        Author author = AuthorMapper.INSTANCE.authorToAddAuthorRequest(request);

        Author saved = authorRepository.save(author);

        AddAuthorResponse response = AuthorMapper.INSTANCE.addAuthorResponseToAuthor(saved);
        return response;
    }
}
