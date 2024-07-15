package com.maxiflexy.springbootlibraryapp.controller;

import com.maxiflexy.springbootlibraryapp.entity.Book;
import com.maxiflexy.springbootlibraryapp.service.BookService;
import com.maxiflexy.springbootlibraryapp.utils.ExtractJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @PutMapping("/secure/checkout")
    public Book checkoutBook( @RequestHeader(value = "Authorization") String token, @RequestParam Long bookId) throws Exception{
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.checkoutBook(userEmail, bookId);
    }

    @GetMapping("/secure/is-checkout/by-user")
    public boolean checkoutBookByUser(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId){
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.checkoutBookByUser(userEmail, bookId);
    }

    @GetMapping("/secure/current-loans/count")
    public int currentLoansCount(@RequestHeader(value = "Authorization") String token){
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return bookService.currentLoansCount(userEmail);
    }
}
