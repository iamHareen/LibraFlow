package com.hareendev.libraflow.repository;

import com.hareendev.libraflow.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
