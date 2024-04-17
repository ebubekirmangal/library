package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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

    @Column(name="recived_date")
    private LocalDateTime pickUpDate;

    @Column(name ="delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name ="delay_date")
    private LocalDateTime delayDate;

    @OneToMany(mappedBy = "borrow")
    private List<Book> books;

    @ManyToOne
    @JoinColumn(name = "tc_num")
    private User user;

}
