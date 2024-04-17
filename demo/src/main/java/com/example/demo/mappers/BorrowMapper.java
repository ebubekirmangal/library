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
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface BorrowMapper {

    BorrowMapper INSTANCE = Mappers.getMapper(BorrowMapper.class);


    Borrow borrowToAddBorrowRequest(AddBorrowRequest request);

    AddBorrowResponse addBorrowResponse(Borrow borrow);

    Borrow borrowToUptadeBorrowRequest(UpdateBorrowRequest request);

    UpdateBorrowResponse updateBorrowResponseToBorrow(Borrow borrow);

    DeleteBorrowResponse deleteBorrowResponseToBorrow(Borrow borrow);

    GetAllBorrowResponse getAllBorrowResponse(Borrow borrow);

    default List<String> mapBorrowsToBookName(List<Book> books) {
        if (books == null) {
            return null;
        }
        return books.stream()
                .map(book->book.getName()) // Corrected method reference
                .collect(Collectors.toList());
    }

    default List<Integer> mapBorrowsToBookId(List<Book> books) {
        if (books == null) {
            return null;
        }
        return books.stream()
                .map(book-> book.getId()) // Corrected method reference
                .collect(Collectors.toList());
    }
}
