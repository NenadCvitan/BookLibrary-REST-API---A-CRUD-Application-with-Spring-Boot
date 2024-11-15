package com.example.crud.Entitys;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String title;
    private String author;
    private LocalDate publicationDate;
    private String genre;
    private String isbn;
    private Integer numberOfPages;
    private String publisher;
    private String language;
    private String description;
    private Double price;
    private String coverImage;
    private String availability;


}