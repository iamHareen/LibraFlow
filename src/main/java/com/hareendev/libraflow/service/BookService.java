package com.hareendev.libraflow.service;

import com.hareendev.libraflow.dto.BookDTO;
import com.hareendev.libraflow.model.Book;
import com.hareendev.libraflow.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book Not Found"));
        return book;
    }

    public Book addBook(BookDTO bookDTO) {
//        log.info("DTO received: {}", bookDTO);
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setQuantity(bookDTO.getQuantity());
        book.setAvailable(bookDTO.isAvailable());
//        log.info("Book before save: {}", book);
        Book saved = bookRepository.save(book);
//        log.info("Book after save: {}", saved);
        return saved;
    }

    public Book updateBook(Long id, BookDTO bookDTO) {
        Book oldBook = bookRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Book Not Found"));

        oldBook.setTitle(bookDTO.getTitle());
        oldBook.setAuthor(bookDTO.getAuthor());
        oldBook.setIsbn(bookDTO.getIsbn());
        oldBook.setAvailable(bookDTO.isAvailable());
        oldBook.setAvailable(bookDTO.isAvailable());
        return bookRepository.save(oldBook);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
