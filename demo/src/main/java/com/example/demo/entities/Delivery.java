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

    private  double totalFee;

    private long delayDay;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
//    @OneToMany(mappedBy = "delivery")
//    private List<Punishment> punishments;

    @OneToOne
    @JoinColumn(name = "borrow_id")
    private Borrow borrow;
}
