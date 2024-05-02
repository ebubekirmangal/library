package com.example.demo.services.mappers;

import com.example.demo.entities.Book;
import com.example.demo.entities.Borrow;
import com.example.demo.services.dtos.requests.borrow.AddBorrowRequest;
import com.example.demo.services.dtos.requests.borrow.UpdateBorrowRequest;
import com.example.demo.services.dtos.responses.borrow.AddBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.DeleteBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.GetAllBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.UpdateBorrowResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface BorrowMapper {

    BorrowMapper INSTANCE = Mappers.getMapper(BorrowMapper.class);

    @Mapping(target = "user.tcNum", source = "tcNum")
    @Mapping(target = "books", source = "bookIds")
    Borrow borrowToAddBorrowRequest(AddBorrowRequest request);

    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "bookNames",source = "books")
    AddBorrowResponse addBorrowResponse(Borrow borrow);
    @Mapping(target = "user.tcNum", source = "tcNum")
    @Mapping(target = "books", source = "bookIds")
    Borrow borrowToUpdateBorrowRequest(UpdateBorrowRequest request);

    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "bookNames",source = "books")
    UpdateBorrowResponse updateBorrowResponseToBorrow(Borrow borrow);

    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "bookNames",source = "books")
    DeleteBorrowResponse deleteBorrowResponseToBorrow(Borrow borrow);

    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "bookNames",source = "books")
    GetAllBorrowResponse getAllBorrowResponse(Borrow borrow);

    default List<Integer> mapToBookIds(List<Book> books){

        return books.stream()
                .map(Book::getId)
                .collect(Collectors.toList());
    }
    default Book findBookById(Integer bookId) {
        // Burada bookRepository veya başka bir mekanizma ile belirli bir kitabı bulunabilir.
        return null; // Örnek olarak null döndürüldü, gerçek projede uygun bir şekilde implemente edilmelidir.
    }
    default List<String> mapToBookNames(List<Book> books){

        return books.stream()
                .map(Book::getName)
                .collect(Collectors.toList());
    }

}
