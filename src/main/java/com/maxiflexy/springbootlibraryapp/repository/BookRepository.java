package com.maxiflexy.springbootlibraryapp.repository;

import com.maxiflexy.springbootlibraryapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
