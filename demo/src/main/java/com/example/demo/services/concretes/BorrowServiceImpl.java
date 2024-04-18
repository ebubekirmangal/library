package com.example.demo.services.concretes;

import com.example.demo.core.utils.exceptions.types.BusinessException;
import com.example.demo.entities.Book;
import com.example.demo.entities.Borrow;
import com.example.demo.entities.User;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.BorrowRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.abstracts.BorrowService;
import com.example.demo.services.dtos.requests.borrow.AddBorrowRequest;
import com.example.demo.services.dtos.requests.borrow.DeleteBorrowRequest;
import com.example.demo.services.dtos.requests.borrow.UpdateBorrowRequest;
import com.example.demo.services.dtos.responses.borrow.AddBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.DeleteBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.GetAllBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.UpdateBorrowResponse;
import com.example.demo.services.mappers.BorrowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowServiceImpl implements BorrowService {

    private BorrowRepository borrowRepository;

    private UserRepository userRepository;

    private BookRepository bookRepository;


    public BorrowServiceImpl(BorrowRepository borrowRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.borrowRepository = borrowRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public AddBorrowResponse add(AddBorrowRequest request) {
        Borrow borrow = BorrowMapper.INSTANCE.borrowToAddBorrowRequest(request);

        int bookId = request.getBookId();
        Book book = bookRepository.findById(bookId).orElseThrow(()->new BusinessException("id bulunamadı."));

        String tcNum = request.getTcNum();
        User user = userRepository.findByTcNum(tcNum);
        if(user == null){
            throw new BusinessException("Tc numarası bulunamadı.");
        }

        borrow.setBook(book);
        borrow.setUser(user);
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
