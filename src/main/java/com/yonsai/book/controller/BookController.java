package com.yonsai.book.controller;

import com.yonsai.book.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 1. 도서 등록 (Add) - 진짜 DB 저장으로 업그레이드!
    @GetMapping("/add")
    public String add(@RequestParam("title") String title, 
                      @RequestParam("author") String author) {
        bookService.addBook(title, author);
        return "도서 등록 완료: " + title + " (저자: " + author + ")";
    }

    // (update, select는 일단 그대로 둡니다)
    @GetMapping("/update")
    public String update(@RequestParam(value = "id", required = false) String id) {
        return "도서 수정 완료 ID: " + id;
    }

    @GetMapping("/select")
    public String select() {
        return "도서 전체 조회 결과입니다.";
    }
}