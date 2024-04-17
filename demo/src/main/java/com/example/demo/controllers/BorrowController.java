package com.example.demo.controllers;

import com.example.demo.services.abstracts.BorrowService;
import com.example.demo.services.dtos.requests.borrow.AddBorrowRequest;
import com.example.demo.services.dtos.requests.borrow.DeleteBorrowRequest;
import com.example.demo.services.dtos.requests.borrow.UpdateBorrowRequest;
import com.example.demo.services.dtos.responses.borrow.DeleteBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.GetAllBorrowResponse;
import com.example.demo.services.dtos.responses.borrow.UpdateBorrowResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    private BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AddBorrowResponse add(AddBorrowRequest request){
        return  borrowService.add(request);
    }

    @PutMapping("/update")
    public UpdateBorrowResponse update(UpdateBorrowRequest request){
        return borrowService.update(request);
    }

    @DeleteMapping("/delete")
    public DeleteBorrowResponse delete(DeleteBorrowRequest request){
        return borrowService.delete(request);
    }

    @GetMapping("/getAll")
    public List<GetAllBorrowResponse> getAll(){
        return borrowService.getAll();
    }
}
