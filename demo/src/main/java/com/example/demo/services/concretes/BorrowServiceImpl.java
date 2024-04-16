package com.example.demo.services.concretes;

import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BorrowRepository;
import com.example.demo.services.abstracts.BookService;
import com.example.demo.services.abstracts.BorrowService;
import org.springframework.stereotype.Service;

@Service
public class BorrowServiceImpl implements BorrowService {

    private BorrowRepository borrowRepository;

    public BorrowServiceImpl(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }
}
