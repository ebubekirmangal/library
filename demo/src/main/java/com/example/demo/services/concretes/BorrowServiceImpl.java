package com.example.demo.services.concretes;

import com.example.demo.core.utils.exceptions.types.BusinessException;
import com.example.demo.entities.Borrow;
import com.example.demo.services.mappers.BorrowMapper;
import com.example.demo.repositories.BorrowRepository;
import com.example.demo.services.abstracts.BorrowService;
import com.example.demo.services.dtos.requests.borrow.AddBorrowRequest;
import com.example.demo.services.dtos.requests.borrow.DeleteBorrowRequest;
import com.example.demo.services.dtos.requests.borrow.UpdateBorrowRequest;
import com.example.demo.services.dtos.responses.borrow.AddBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.DeleteBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.GetAllBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.UpdateBorrowResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public UpdateBorrowResponse update(UpdateBorrowRequest request) {
        Borrow borrow = BorrowMapper.INSTANCE.borrowToUpdateBorrowRequest(request);
        Borrow receivedDate = borrowRepository.save(borrow);
        UpdateBorrowResponse response = BorrowMapper.INSTANCE.updateBorrowResponseToBorrow(receivedDate);
        return response;
    }

    @Override
    public DeleteBorrowResponse delete(DeleteBorrowRequest request) {
        Borrow borrowId = borrowRepository.findById(request.getId()).orElseThrow(()->new BusinessException("id bulunamadı."));
        borrowRepository.delete(borrowId);

        DeleteBorrowResponse response = BorrowMapper.INSTANCE.deleteBorrowResponseToBorrow(borrowId);

        return response;
    }

    @Override
    public List<GetAllBorrowResponse> getAll() {
        List<Borrow> borrows = borrowRepository.findAll();
        List<GetAllBorrowResponse> result = new ArrayList<>();

        for(Borrow borrow:borrows){
            GetAllBorrowResponse dto = BorrowMapper.INSTANCE.getAllBorrowResponse(borrow);
            result.add(dto);
        }

        return result;
    }

}
