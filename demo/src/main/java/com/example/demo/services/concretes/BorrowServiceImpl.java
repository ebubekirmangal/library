package com.example.demo.services.concretes;

import com.example.demo.core.utils.exceptions.types.BusinessException;
import com.example.demo.entities.*;
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

import java.time.LocalDate;
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
    public AddBorrowResponse add(AddBorrowRequest request) {//TODO: Aynı kitaptan sadece 1 tane alabilir teslim edilmediyse

        Borrow borrow = BorrowMapper.INSTANCE.borrowToAddBorrowRequest(request);


        List<Book> books = bookService.findAllById(request.getBookIds());
        if (books.isEmpty()) {
            throw new BusinessException("Kitap bulunamadı.");
        }

        for (Book book : books) {
            if (book.getBookStatus() == BookStatus.AtTheVisitor) {
                throw new BusinessException("Kitap zaten kullanımda.");
            }
        }
        LocalDate pickUpDate = LocalDate.now();
        borrow.setPickUpDate(pickUpDate);
        String tcNum = request.getTcNum();
        User user = userService.findByTcNum(tcNum);
        borrow.setBooks(books);
        borrow.setUser(user);

            if(!user.getIsActionTake()){
            throw new BusinessException("Kullanıcının borcu gözükmektedir ya da Ödünç alınan kitap teslim edilmemiştir.");
        }
            Borrow saved = borrowRepository.save(borrow);
        return BorrowMapper.INSTANCE.addBorrowResponse(saved);

    }
    public UpdateBorrowResponse update(UpdateBorrowRequest request) {
        Borrow borrow = BorrowMapper.INSTANCE.borrowToUpdateBorrowRequest(request);
        Borrow receivedDate = borrowRepository.save(borrow);
        return BorrowMapper.INSTANCE.updateBorrowResponseToBorrow(receivedDate);
    }
    @Override
    public DeleteBorrowResponse delete(int id) {
        Borrow borrowId = borrowRepository.findById(id).orElseThrow(()->new BusinessException("id bulunamadı."));
        borrowRepository.delete(borrowId);

        return BorrowMapper.INSTANCE.deleteBorrowResponseToBorrow(borrowId);
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

        return borrowRepository.findById(id).orElseThrow(()-> new BusinessException("Borrow id bulunamadı"));

    }


}
