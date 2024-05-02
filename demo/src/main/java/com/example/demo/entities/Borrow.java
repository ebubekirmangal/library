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
    public Borrow(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="pick_up_date")
    private LocalDate pickUpDate;

    @Column(name = "dead_line")
    private LocalDate deadLine;

    @ManyToMany(fetch = FetchType.EAGER)//TODO:sorum olucak hocaya
    @JoinTable(name = "book_borrow",
    joinColumns = @JoinColumn(name = "borrow_id"),
    inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    @ManyToOne
    @JoinColumn(name = "tc_num")
    private User user;
    @OneToOne(mappedBy = "borrow")
    private Delivery delivery;
}
