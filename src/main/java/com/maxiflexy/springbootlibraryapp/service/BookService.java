package com.maxiflexy.springbootlibraryapp.service;

import com.maxiflexy.springbootlibraryapp.entity.Book;

public interface BookService {

    Book checkoutBook(String userEmail, Long bookId) throws Exception;

    boolean checkoutBookByUser(String userEmail, Long bookId);
}
