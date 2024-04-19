package com.example.demo.services.concretes;

import com.example.demo.core.utils.exceptions.types.BusinessException;
import com.example.demo.entities.Book;
import com.example.demo.entities.Borrow;
import com.example.demo.entities.User;
import com.example.demo.repositories.BorrowRepository;
import com.example.demo.services.abstracts.BookService;
import com.example.demo.services.abstracts.BorrowService;
import com.example.demo.services.abstracts.UserService;
import com.example.demo.services.dtos.requests.borrow.AddBorrowRequest;
import com.example.demo.services.dtos.requests.borrow.UpdateBorrowRequest;
import com.example.demo.services.dtos.responses.borrow.AddBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.DeleteBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.GetAllBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.UpdateBorrowResponse;
import com.example.demo.services.mappers.BorrowMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    private BorrowRepository borrowRepository;
    private UserService userService;
    private BookService bookService;

    public BorrowServiceImpl(BorrowRepository borrowRepository, UserService userService, BookService bookService) {
        this.borrowRepository = borrowRepository;
        this.userService = userService;
        this.bookService = bookService;
    }
    @Override
    public AddBorrowResponse add(AddBorrowRequest request) {
        Borrow borrow = BorrowMapper.INSTANCE.borrowToAddBorrowRequest(request);

        int bookId = request.getBookId();
        Book book = bookService.findById(bookId);

        String tcNum = request.getTcNum();
        User user = userService.findByTcNum(tcNum);
        borrow.getBook().setIsBorrow(true);
        borrow.setBook(book);
        borrow.setUser(user);
        try {
            Borrow saved = borrowRepository.save(borrow);
            AddBorrowResponse response = BorrowMapper.INSTANCE.addBorrowResponse(saved);
            return response;
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Bu kitap zaten ödünç alınmış.");
        }
    }
    public UpdateBorrowResponse update(UpdateBorrowRequest request) {
        Borrow borrow = BorrowMapper.INSTANCE.borrowToUpdateBorrowRequest(request);
        Borrow receivedDate = borrowRepository.save(borrow);
        UpdateBorrowResponse response = BorrowMapper.INSTANCE.updateBorrowResponseToBorrow(receivedDate);
        return response;
    }
    @Override
    public DeleteBorrowResponse delete(int id) {
        Borrow borrowId = borrowRepository.findById(id).orElseThrow(()->new BusinessException("id bulunamadı."));
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

    @Override
    public Borrow findById(int id) {

        return borrowRepository.findById(id).orElseThrow(()-> new BusinessException("id bulunamadı"));

    }


}
