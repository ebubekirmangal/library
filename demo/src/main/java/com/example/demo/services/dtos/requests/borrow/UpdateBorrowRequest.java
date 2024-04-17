package com.example.demo.services.dtos.requests.borrow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBorrowRequest {

    private int id;

    private String tcNum;

    private List<Integer> bookIds;

    private LocalDateTime pickUpDate;
}
