package com.maxiflexy.springbootlibraryapp.service.impl;

import com.maxiflexy.springbootlibraryapp.entity.Book;
import com.maxiflexy.springbootlibraryapp.payloads.request.AddBookRequest;
import com.maxiflexy.springbootlibraryapp.repository.BookRepository;
import com.maxiflexy.springbootlibraryapp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final BookRepository bookRepository;

    public void postBook(AddBookRequest addBookRequest){

        Book book = new Book();
        book.setTitle(addBookRequest.getTitle());
        book.setAuthor(addBookRequest.getAuthor());
        book.setDescription(addBookRequest.getDescription());
        book.setCopies(addBookRequest.getCopies());
        book.setCopiesAvailable(addBookRequest.getCopies());
        book.setCategory(addBookRequest.getCategory());
        book.setImg(addBookRequest.getImg());
        bookRepository.save(book);
    }
}
