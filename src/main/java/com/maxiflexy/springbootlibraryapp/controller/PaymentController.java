package com.maxiflexy.springbootlibraryapp.controller;

import com.maxiflexy.springbootlibraryapp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("https://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment/secure")
public class PaymentController {

    private final PaymentService paymentService;


}
