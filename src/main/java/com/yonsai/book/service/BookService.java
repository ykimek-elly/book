package com.yonsai.book.service;

import com.yonsai.book.domain.Book;
import com.yonsai.book.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 도서 저장 기능
    public Book addBook(String title, String author) {
        Book book = new Book(title, author);
        return bookRepository.save(book); // DB에 저장!
    }
}