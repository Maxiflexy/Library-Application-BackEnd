package com.maxiflexy.springbootlibraryapp.service;

import com.maxiflexy.springbootlibraryapp.payloads.request.AddBookRequest;

public interface AdminService {

    void postBook(AddBookRequest addBookRequest);

    void increaseBookQuantity(Long bookId) throws Exception;

    void decreaseBookQuantity(Long bookId) throws Exception;
}
