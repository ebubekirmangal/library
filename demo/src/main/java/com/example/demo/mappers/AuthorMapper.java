package com.example.demo.mappers;

import com.example.demo.entities.Author;
import com.example.demo.services.dtos.requests.author.AddAuthorRequest;
import com.example.demo.services.dtos.requests.author.UpdateAuthorRequest;
import com.example.demo.services.dtos.responses.author.AddAuthorResponse;
import com.example.demo.services.dtos.responses.author.DeleteAuthorResponse;
import com.example.demo.services.dtos.responses.author.GetAllAuthorResponse;
import com.example.demo.services.dtos.responses.author.UpdateAuthorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    Author authorToAddAuthorRequest(AddAuthorRequest request);

    AddAuthorResponse addAuthorResponseToAuthor(Author author);

    Author authorToUpdateAuthorRequest(UpdateAuthorRequest request);

    UpdateAuthorResponse updateAuthorResponseToAuthor(Author author);

    DeleteAuthorResponse deleteAuthorResponseToAuthor(Author author);

    GetAllAuthorResponse getAllAuthorResponseToAuthor(Author author);


}
