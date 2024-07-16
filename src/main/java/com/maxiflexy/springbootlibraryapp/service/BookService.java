package com.maxiflexy.springbootlibraryapp.service;

import com.maxiflexy.springbootlibraryapp.entity.Book;
import com.maxiflexy.springbootlibraryapp.payloads.response.ShelfCurrentLoansResponse;

import java.util.List;

public interface BookService {

    Book checkoutBook(String userEmail, Long bookId) throws Exception;

    boolean checkoutBookByUser(String userEmail, Long bookId);

    int currentLoansCount(String userEmail);

    public List<ShelfCurrentLoansResponse> currentLoans(String userEmail) throws Exception;
}
