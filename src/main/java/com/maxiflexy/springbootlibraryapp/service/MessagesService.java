package com.maxiflexy.springbootlibraryapp.service;

import com.maxiflexy.springbootlibraryapp.entity.Message;
import com.maxiflexy.springbootlibraryapp.payloads.request.AdminQuestionRequest;

public interface MessagesService {

    void postMessage(Message messageRequest, String userEmail);

    void putMessage(AdminQuestionRequest adminQuestionRequest, String userEmail) throws Exception;
}
