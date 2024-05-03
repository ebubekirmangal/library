package com.example.demo.services.mappers;

import com.example.demo.entities.Borrow;
import com.example.demo.services.dtos.requests.borrow.AddBorrowRequest;
import com.example.demo.services.dtos.requests.borrow.UpdateBorrowRequest;
import com.example.demo.services.dtos.responses.borrow.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BorrowMapper {

    BorrowMapper INSTANCE = Mappers.getMapper(BorrowMapper.class);

    @Mapping(target = "user.tcNum", source = "tcNum")
    @Mapping(target = "book.id", source = "bookId")
    Borrow borrowToAddBorrowRequest(AddBorrowRequest request);

    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "bookName",source = "book.name")
    @Mapping(target = "bookStatus",source = "book.bookStatus")
    AddBorrowResponse addBorrowResponse(Borrow borrow);
    @Mapping(target = "user.tcNum", source = "tcNum")
    @Mapping(target = "book.id", source = "bookId")
    Borrow borrowToUpdateBorrowRequest(UpdateBorrowRequest request);

    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "bookName",source = "book.name")
    UpdateBorrowResponse updateBorrowResponseToBorrow(Borrow borrow);

    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "bookName",source = "book.name")
    DeleteBorrowResponse deleteBorrowResponseToBorrow(Borrow borrow);

    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "bookName",source = "book.name")
    GetAllBorrowResponse getAllBorrowResponse(Borrow borrow);
    @Mapping(target = "firstName", source = "response.firstName")
    @Mapping(target = "lastName", source = "response.lastName")
    @Mapping(target = "tcNum", source = "response.tcNum")
    @Mapping(target = "bookCount", source = "response.bookCount")
    GetAllUserWhoBorrowsTheMostBooksResponse userWithMostBorrows(GetAllTop3UserByMostBorrowResponse response);
}
