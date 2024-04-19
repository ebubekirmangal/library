package com.example.demo.services.mappers;

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
    @Mapping(target = "category.id",source = "categoryId")
    Book bookToAddBookRequest(AddBookRequest request);

    @Mapping(target = "authorFirstName",source = "author.firstName")
    @Mapping(target = "authorLastName",source = "author.lastName")
    @Mapping(target = "authorId" ,source = "author.id")
    @Mapping(target = "categoryName" , source = "category.name")
    AddBookResponse addBookResponseToBook(Book book);

    @Mapping(target = "author.id" ,source = "authorId")
    @Mapping(target = "category.id",source = "categoryId")
    Book bookToUpdateBookRequest(UpdateBookRequest request);
    @Mapping(target = "authorFirstName",source = "author.firstName")
    @Mapping(target = "authorLastName",source = "author.lastName")
    @Mapping(target = "authorId" ,source = "author.id")
    @Mapping(target = "categoryName" , source = "category.name")
    UpdateBookResponse updateBookResponseToBook(Book book);

    @Mapping(target = "authorFirstName",source = "author.firstName")
    @Mapping(target = "authorLastName",source = "author.lastName")
    @Mapping(target = "authorId" ,source = "author.id")
    @Mapping(target = "categoryName" , source = "category.name")
    DeleteBookResponse deleteBookResponseToBook(Book book);
    @Mapping(target = "authorFirstName",source = "author.firstName")
    @Mapping(target = "authorLastName",source = "author.lastName")
    @Mapping(target = "authorId" ,source = "author.id")
    @Mapping(target = "categoryName" , source = "category.name")
    GetAllBookResponse getAllBookResponseToBook(Book book);
}
