package com.yonsai.book.repository;

import com.yonsai.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    // 텅 비어 있어도 JPA가 알아서 save, findAll 등을 만들어줍니다.
}