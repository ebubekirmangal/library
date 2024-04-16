package com.example.demo.mappers;

import com.example.demo.entities.Book;
import com.example.demo.services.dtos.requests.book.AddBookRequest;
import com.example.demo.services.dtos.requests.book.UpdateBookRequest;
import com.example.demo.services.dtos.responses.book.AddBookResponse;
import com.example.demo.services.dtos.responses.book.DeleteBookResponse;
import com.example.demo.services.dtos.responses.book.GetAllBookResponse;
import com.example.demo.services.dtos.responses.book.UpdateBookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "author.id" ,source = "authorId")
    Book bookToAddBookRequest(AddBookRequest request);

    @Mapping(target = "authorFirstName",source = "author.firstName")
    @Mapping(target = "authorLastName",source = "author.lastName")
    @Mapping(target = "authorId" ,source = "author.id")
    AddBookResponse addBookResponsetoBook(Book book);

    @Mapping(target = "author.id" ,source = "authorId")
    Book bookToUpdateBookRequest(UpdateBookRequest request);
    @Mapping(target = "authorFirstName",source = "author.firstName")
    @Mapping(target = "authorLastName",source = "author.lastName")
    @Mapping(target = "authorId" ,source = "author.id")
    UpdateBookResponse updateBookResponseToBook(Book book);

    @Mapping(target = "authorFirstName",source = "author.firstName")
    @Mapping(target = "authorLastName",source = "author.lastName")
    @Mapping(target = "authorId" ,source = "author.id")
    DeleteBookResponse deleteBookResponseToBook(Book book);
    @Mapping(target = "authorFirstName",source = "author.firstName")
    @Mapping(target = "authorLastName",source = "author.lastName")
    @Mapping(target = "authorId" ,source = "author.id")
    GetAllBookResponse getAllBookResponseToBook(Book book);
}
