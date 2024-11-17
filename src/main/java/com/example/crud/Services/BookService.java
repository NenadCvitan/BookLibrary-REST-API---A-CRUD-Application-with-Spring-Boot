package com.example.crud.Services;

import com.example.crud.Entitys.Book;
import com.example.crud.Repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;


    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }



    public Book addBook(Book book) {
        return bookRepository.save(book);
    }


    public Optional<Book> findById(Integer id) {
        return Optional.ofNullable(bookRepository.findById(id))
                .orElseThrow(() -> new IllegalArgumentException("Book" +
                        "with ID" + id + "not found"));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findByIsbn(String isbn) {
        if (isbn == null) {
            throw new IllegalArgumentException("ISBN-Number cannot be null");
        }
        return bookRepository.findByIsbn(isbn);
    }


    public List<Book> searchBooks(String title, String author, String publisher, Double price) {
        if (title != null) {
            return bookRepository.findByTitle(title);
        } else if (author != null) {
            return bookRepository.findByAuthor(author);
        } else if (publisher != null) {
            return bookRepository.findByPublisher(publisher);
        } else if (price != null) {
            return bookRepository.findByPrice(price);
        }
        return bookRepository.findAll();
    }


    public List<Book> findByGenre(String genre){
        if (genre != null){
            return bookRepository.findByGenre(genre);
        }
        return new ArrayList<>();
    }




    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }



    // Update Book Method
    public Book updateBook(Integer id, Book bookDetails) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book with ID " + id + " not found"));

        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setAuthor(bookDetails.getAuthor());
        existingBook.setPublisher(bookDetails.getPublisher());
        existingBook.setPrice(bookDetails.getPrice());
        existingBook.setGenre(bookDetails.getGenre());
        existingBook.setIsbn(bookDetails.getIsbn());

        return bookRepository.save(existingBook);
    }







}