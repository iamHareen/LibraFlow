package com.hareendev.libraflow.service;

import com.hareendev.libraflow.dto.BookDTO;
import com.hareendev.libraflow.model.Book;
import com.hareendev.libraflow.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setAvailable(bookDTO.isAvailable());
        book.setQuantity(bookDTO.getQuantity());
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, BookDTO bookDTO) {
        Book oldBook = bookRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Book Not Found"));

        oldBook.setTitle(bookDTO.getTitle());
        oldBook.setAuthor(bookDTO.getAuthor());
        oldBook.setIsbn(bookDTO.getIsbn());
        oldBook.setAvailable(bookDTO.isAvailable());
        oldBook.setQuantity(bookDTO.getQuantity());
        return bookRepository.save(oldBook);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
