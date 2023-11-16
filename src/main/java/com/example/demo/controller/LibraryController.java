package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookAvailability;
import com.example.demo.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @GetMapping("/availableBooks")
    public List<Book> getAvailableBooks(){
        return libraryService.getAvailableBooks();
    }

    @PostMapping("/borrow/{bookId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId) {
        BookAvailability borrowedBook = libraryService.borrowBook(bookId);
        if (borrowedBook != null) {
            return new ResponseEntity<>("Book with id " + bookId + " has been borrowed.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to borrow book with id " + bookId, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/return/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        BookAvailability returnedBook = libraryService.returnBook(bookId);
        if (returnedBook != null) {
            return new ResponseEntity<>("Book with id " + bookId + " has been returned.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to return book with id " + bookId, HttpStatus.BAD_REQUEST);
        }
    }
}
