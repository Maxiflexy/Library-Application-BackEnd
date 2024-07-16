package com.maxiflexy.springbootlibraryapp.controller;

import com.maxiflexy.springbootlibraryapp.entity.Message;
import com.maxiflexy.springbootlibraryapp.service.MessagesService;
import com.maxiflexy.springbootlibraryapp.utils.ExtractJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
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
}
