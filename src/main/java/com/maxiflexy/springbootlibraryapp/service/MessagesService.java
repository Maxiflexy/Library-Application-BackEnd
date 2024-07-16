package com.maxiflexy.springbootlibraryapp.service;

import com.maxiflexy.springbootlibraryapp.entity.Message;

public interface MessagesService {

    void postMessage(Message messageRequest, String userEmail);
}
