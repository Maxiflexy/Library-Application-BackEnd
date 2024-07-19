package com.maxiflexy.springbootlibraryapp.controller;

import com.maxiflexy.springbootlibraryapp.entity.Message;
import com.maxiflexy.springbootlibraryapp.payloads.request.AdminQuestionRequest;
import com.maxiflexy.springbootlibraryapp.service.MessagesService;
import com.maxiflexy.springbootlibraryapp.utils.ExtractJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessagesController {

    private final MessagesService messagesService;

    @PostMapping("/secure/add/message")
    public void postMessage(@RequestHeader(value = "Authorization")
                                String token, @RequestBody Message messageRequest){

        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        messagesService.postMessage(messageRequest, userEmail);
    }

    @PutMapping("/secure/admin/message")
    public void putMessage(@RequestHeader(value = "Authorization")
                               String token, @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception{

        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"email\"");
        String admin2 = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");


        System.out.println(admin);
        System.out.println(userEmail);

        messagesService.putMessage(adminQuestionRequest, userEmail);
    }
}
