package com.example.demo.services.mappers;

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

@Mapper
public interface BorrowMapper {

    BorrowMapper INSTANCE = Mappers.getMapper(BorrowMapper.class);

    @Mapping(target = "user.tcNum", source = "tcNum")
    @Mapping(target = "book.id", source = "bookId")
    Borrow borrowToAddBorrowRequest(AddBorrowRequest request);

    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "bookName",source = "book.name")

    @Mapping(target = "totalFee",source = "delivery.totalFee")
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


}
