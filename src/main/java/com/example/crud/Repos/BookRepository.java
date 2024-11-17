package com.example.crud.Repos;

import com.example.crud.Entitys.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


    Optional<Book> findById(Integer id);
    Optional<Book> findByIsbn(String Isbn);


    //Methods to find several books in a list
    List<Book> findAll();
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByPublisher(String publisher);
    List<Book> findByPrice(Double price);
    List<Book> findByGenre(String genre);

    //   Put/Update Methods









}
