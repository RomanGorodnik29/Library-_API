package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookAvailability;
import com.example.demo.exception.NoAvailableBooksException;
import com.example.demo.repository.BookAvailabilityRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private final BookAvailabilityRepository bookAvailabilityRepository;
    @Autowired
    public LibraryService(BookAvailabilityRepository bookAvailabilityRepository) {
        this.bookAvailabilityRepository = bookAvailabilityRepository;
    }

    @Autowired
    private BookRepository bookRepository;


    public List<Book> getAvailableBooks() {
        List<BookAvailability> availableAvailabilities = bookAvailabilityRepository.findByDueTimeIsNull();
        List<Book> availableBooks = availableAvailabilities.stream()
                .map(BookAvailability::getBook)
                .collect(Collectors.toList());
        if (availableAvailabilities.isEmpty()) {
            throw new NoAvailableBooksException("No available books found."); // Custom exception
        }
        return availableBooks;
    }

    public BookAvailability borrowBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            BookAvailability bookAvailability = new BookAvailability();
            bookAvailability.setBook(book);
            bookAvailability.setBorrowedTime(LocalDateTime.now());
            // Логика установки даты возврата, например, через 14 дней
            bookAvailability.setDueTime(LocalDateTime.now().plusDays(14));
            return bookAvailabilityRepository.save(bookAvailability);
        }
        return null;
    }
    public BookAvailability returnBook(Long bookId) {
        BookAvailability bookAvailability = bookAvailabilityRepository.findById(bookId).orElse(null);
        if (bookAvailability != null) {
            bookAvailability.setDueTime(LocalDateTime.now()); // Устанавливаем факт возврата книги
            return bookAvailabilityRepository.save(bookAvailability);
        }
        return null;
    }
}