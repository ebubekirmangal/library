package com.example.demo.services.concretes;

import com.example.demo.entities.Borrow;
import com.example.demo.mappers.BorrowMapper;
import com.example.demo.repositories.BorrowRepository;
import com.example.demo.services.abstracts.BorrowService;
import com.example.demo.services.dtos.requests.borrow.AddBorrowRequest;
import com.example.demo.services.dtos.requests.borrow.DeleteBorrowRequest;
import com.example.demo.services.dtos.requests.borrow.UpdateBorrowRequest;
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
        Borrow borrow = BorrowMapper.INSTANCE.borrowToAddBorrowRequest(request);
        Borrow saved = borrowRepository.save(borrow);

        AddBorrowResponse response = BorrowMapper.INSTANCE.addBorrowResponse(saved);
        return response;
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
