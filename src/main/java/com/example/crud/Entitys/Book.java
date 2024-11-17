package com.example.crud.Entitys;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.time.LocalDate;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String title; //Title of book 1
    private String author; //Author of the book 2
    private LocalDate publicationDate; //Publication date of the book
    private String genre; //Genre of the book
    private String isbn; // ISBN number of book
    private Integer numberOfPages; // number of pages in the book
    private String publisher; //Publisher of the book 3
    private String language; //Language of the book
    private String description; //Description of the book
    private Double price; //Price of the book 4
    private String coverImage; //Path of the book
    private String availability; // Availability status


}