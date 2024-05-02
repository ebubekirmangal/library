package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Table(name = "deliveries")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "received_date")
    private LocalDate receivedDate;

    @Column(name = "total_fee")
    private  Double totalFee;

    @Column(name = "penalty_fee")
    private double penaltyFee;
    @Column(name = "delay_day")
    private long delayDay;

    private String message;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany
    @JoinTable(name = "book_delivery",
    joinColumns = @JoinColumn(name = "delivery_id"),
    inverseJoinColumns = @JoinColumn(name ="book_id"))
    private List<Book> books;
    @OneToOne
    @JoinColumn(name = "borrow_id")
    private Borrow borrow;
}
