package com.yonsai.book.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    // 1. 도서 등록 (Add)
    @GetMapping("/add")
    public String add(@RequestParam(value = "title", required = false) String title) {
        return "도서 등록 완료: " + title;
    }

    // 2. 도서 수정 (Update)
    @GetMapping("/update")
    public String update(@RequestParam(value = "id", required = false) String id) {
        return "도서 수정 완료 ID: " + id;
    }

    // 3. 도서 조회 (Select)
    @GetMapping("/select")
    public String select() {
        return "도서 전체 조회 결과입니다.";
    }
}