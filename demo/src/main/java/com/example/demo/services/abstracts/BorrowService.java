package com.example.demo.services.abstracts;

import com.example.demo.entities.Borrow;
import com.example.demo.services.dtos.requests.borrow.AddBorrowRequest;
import com.example.demo.services.dtos.requests.borrow.UpdateBorrowRequest;
import com.example.demo.services.dtos.responses.borrow.*;

import java.util.List;

public interface BorrowService {

    AddBorrowResponse add(AddBorrowRequest request);

    UpdateBorrowResponse update(UpdateBorrowRequest request);

    DeleteBorrowResponse delete(int id);

    List<GetAllBorrowResponse> getAll();
    Borrow findById(int id);
    List<Borrow> top3UserByMostBorrow();
}
