package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookAvailabilityRepository extends JpaRepository<BookAvailability, Long> {
    List<BookAvailability> findByDueTimeIsNull();
}