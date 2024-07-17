package com.maxiflexy.springbootlibraryapp.service;

import com.maxiflexy.springbootlibraryapp.payloads.request.AddBookRequest;

public interface AdminService {

    void postBook(AddBookRequest addBookRequest);
}
