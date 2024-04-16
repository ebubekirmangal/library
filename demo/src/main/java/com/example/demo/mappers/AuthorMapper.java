package com.example.demo.mappers;

import com.example.demo.entities.Author;
import com.example.demo.services.dtos.requests.author.AddAuthorRequest;
import com.example.demo.services.dtos.responses.author.AddAuthorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    Author authorToAddAuthorRequest(AddAuthorRequest request);

    AddAuthorResponse addAuthorResponseToAuthor(Author author);
}
