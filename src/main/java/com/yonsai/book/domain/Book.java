package com.yonsai.book.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String author;

    // 기본 생성자 (JPA 필수)
    public Book() {}

    // 편의를 위한 생성자
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Getter (필요시 Setter 추가)
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
}