package com.example.demo.services.abstracts;

import com.example.demo.services.dtos.requests.borrow.AddBorrowRequest;
import com.example.demo.services.dtos.requests.borrow.DeleteBorrowRequest;
import com.example.demo.services.dtos.requests.borrow.UpdateBorrowRequest;
import com.example.demo.services.dtos.responses.borrow.DeleteBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.GetAllBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.UpdateBorrowResponse;

import java.util.List;

public interface BorrowService {

    AddBorrowResponse add(AddBorrowRequest request);

    UpdateBorrowResponse update(UpdateBorrowRequest request);

    DeleteBorrowResponse delete(DeleteBorrowRequest request);

    List<GetAllBorrowResponse> getAll();
}
