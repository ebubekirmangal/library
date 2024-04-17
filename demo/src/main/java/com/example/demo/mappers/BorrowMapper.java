package com.example.demo.mappers;

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

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface BorrowMapper {


    @Mapping(target = "books",source = "bookIds")
    Borrow borrowToAddBorrowRequest(AddBorrowRequest request);

    @Mapping(target = "bookNames",source = "books")
    AddBorrowResponse addBorrowResponse(Borrow borrow);

    @Mapping(target = "books",source = "bookId")
    Borrow borrowToUptadeBorrowRequest(UpdateBorrowRequest request);

    @Mapping(target = "bookNames",source = "books")
    UpdateBorrowResponse updateBorrowResponseToBorrow(Borrow borrow);

    @Mapping(target = "bookNames",source = "books")
    DeleteBorrowResponse deleteBorrowResponseToBorrow(Borrow borrow);

    @Mapping(target = "bookNames",source = "books")
    GetAllBorrowResponse getAllBorrowResponse(Borrow borrow);

    default List<String> mapBorrowsToBookName(List<Book> books) {
        if (books == null) {
            return null;
        }
        return books.stream()
                .map(Book::getName) // Corrected method reference
                .collect(Collectors.toList());
    }

    default List<Integer> mapBorrowsToBookId(List<Book> books) {
        if (books == null) {
            return null;
        }
        return books.stream()
                .map(Book::getId) // Corrected method reference
                .collect(Collectors.toList());
    }
}
