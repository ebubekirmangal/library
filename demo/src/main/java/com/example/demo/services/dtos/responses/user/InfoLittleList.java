package com.example.demo.services.dtos.responses.user;

import com.example.demo.entities.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InfoLittleList {

    private String bookName;

    private BookStatus bookStatus;

    private LocalDate deadLine;
}
