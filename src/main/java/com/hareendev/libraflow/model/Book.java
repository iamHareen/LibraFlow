package com.hareendev.libraflow.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // for getter & setters
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private Integer quantity;
    private boolean isAvailable;

}
