package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "borrows")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="pick_up_date")
    private LocalDate pickUpDate;

    @Column(name ="delivery_date")
    private LocalDate deliveryDate;

    @Column(name ="delay_day")
    private int delayDay;

    @OneToMany(mappedBy = "borrow")
    private List<Book> books;

    @ManyToOne
    @JoinColumn(name = "tc_num")
    private User user;

}
