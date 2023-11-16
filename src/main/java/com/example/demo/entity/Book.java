package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Book")
@JsonIgnoreProperties({"availabilities"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String isbn;
    @Column
    private String title;
    @Column
    private String genre;
    @Column
    private String description;
    @Column
    private String author;

    @OneToMany(mappedBy = "book")
    private List<BookAvailability> availabilities = new ArrayList<>();

    public Book(Long id, String isbn, String title, String genre, String description, String author, List<BookAvailability> availabilities) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.author = author;
        this.availabilities = availabilities;
    }

    public Book() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<BookAvailability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<BookAvailability> availabilities) {
        this.availabilities = availabilities;
    }
}