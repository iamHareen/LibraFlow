package com.hareendev.libraflow.service;

import com.hareendev.libraflow.model.Book;
import com.hareendev.libraflow.model.IssueRecord;
import com.hareendev.libraflow.model.User;
import com.hareendev.libraflow.repository.BookRepository;
import com.hareendev.libraflow.repository.IssueRecordRepository;
import com.hareendev.libraflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.time.LocalDate;

@Service
public class IssueRecordService {

    @Autowired
    private IssueRecordRepository issueRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public IssueRecord issueTheBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()->new RuntimeException("Book not found"));

        if(book.getQuantity() <= 0 || !book.isAvailable()) {
            throw new RuntimeException("Book is not available");
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username)
                .orElseThrow(()-> new RuntimeException("User not found"));

        IssueRecord issueRecord = new IssueRecord();
        issueRecord.setIssueDate(LocalDate.now());
        issueRecord.setDueDate(LocalDate.now().plusDays(14));
        issueRecord.setReturned(false);
        issueRecord.setUser(user);
        issueRecord.setBook(book);

        book.setQuantity(book.getQuantity()-1);
        if(book.getQuantity()==0) {
            book.setAvailable(false);
        }
        bookRepository.save(book);
        return issueRecordRepository.save(issueRecord);
    }

    public IssueRecord returnTheBook(Long issueRecordId) {
        IssueRecord issueRecord = issueRecordRepository.findById(issueRecordId)
                .orElseThrow(()-> new RuntimeException("Issure Record Not Found"));

        if(issueRecord.isReturned()) {
            throw new RuntimeException("Book is already returned");
        }

        Book book = issueRecord.getBook();
        book.setQuantity(book.getQuantity()+1);
        book.setAvailable(true);
        bookRepository.save(book);

        issueRecord.setReturnDate(LocalDate.now());
        issueRecord.setReturned(true);
        return issueRecordRepository.save(issueRecord);
    }
}
