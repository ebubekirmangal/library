package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "isbn")
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "number_of_page")
    private int numberOfPage;

    @Column(name ="book_status")
    private BookStatus bookStatus ;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToMany(mappedBy = "books",fetch = FetchType.LAZY)
    private List<Borrow> borrows;

    @ManyToMany(mappedBy = "books",fetch = FetchType.LAZY)
    private List<Delivery> deliveries;
}
