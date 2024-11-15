package com.example.crud.Services;

import com.example.crud.Entitys.Book;
import com.example.crud.Repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    //Post Methods
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }


    //Get Methods
    public Optional<Book> findById(Integer id) {
        return bookRepository.findById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public String findByIsbn(String isbn) {
     if (isbn == null){
         throw new NullPointerException("Isbn-Number does not exist");
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


    //Delete Methods
    public void deleteById(Integer id){
    bookRepository.deleteById(id);
    }


}
