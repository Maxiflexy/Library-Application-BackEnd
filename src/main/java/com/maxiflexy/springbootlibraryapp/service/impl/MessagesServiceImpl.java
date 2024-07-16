package com.maxiflexy.springbootlibraryapp.service.impl;

import com.maxiflexy.springbootlibraryapp.entity.Message;
import com.maxiflexy.springbootlibraryapp.repository.MessageRepository;
import com.maxiflexy.springbootlibraryapp.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MessagesServiceImpl implements MessagesService {

    private final MessageRepository messageRepository;

    public void postMessage(Message messageRequest, String userEmail){

        Message message = new Message(messageRequest.getTitle(), messageRequest.getQuestion());
        message.setUserEmail(userEmail);
        messageRepository.save(message);

    }
}
