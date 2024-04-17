package com.example.demo.services.concretes;

import com.example.demo.entities.Borrow;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BorrowRepository;
import com.example.demo.services.abstracts.BookService;
import com.example.demo.services.abstracts.BorrowService;
import com.example.demo.services.dtos.requests.borrow.AddBorrowRequest;
import com.example.demo.services.dtos.requests.borrow.DeleteBorrowRequest;
import com.example.demo.services.dtos.requests.borrow.UpdateBorrowRequest;
import com.example.demo.services.dtos.responses.borrow.AddBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.DeleteBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.GetAllBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.UpdateBorrowResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    private BorrowRepository borrowRepository;

    public BorrowServiceImpl(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    @Override
    public AddBorrowResponse add(AddBorrowRequest request) {
        Borrow borrow;
        return null;
    }

    @Override
    public UpdateBorrowResponse update(UpdateBorrowRequest request) {
        return null;
    }

    @Override
    public DeleteBorrowResponse delete(DeleteBorrowRequest request) {
        return null;
    }

    @Override
    public List<GetAllBorrowResponse> getAll() {
        return null;
    }
}
