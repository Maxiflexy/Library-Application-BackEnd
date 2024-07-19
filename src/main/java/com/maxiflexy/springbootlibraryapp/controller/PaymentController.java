package com.maxiflexy.springbootlibraryapp.controller;

import com.maxiflexy.springbootlibraryapp.payloads.request.PaymentInfoRequest;
import com.maxiflexy.springbootlibraryapp.service.PaymentService;
import com.maxiflexy.springbootlibraryapp.utils.ExtractJWT;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment/secure")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfoRequest paymentInfoRequest)
                                        throws StripeException{

        PaymentIntent paymentIntent = paymentService.createPaymentIntent(paymentInfoRequest);
        String paymentString = paymentIntent.toJson();
        System.out.println(paymentIntent);



        return new ResponseEntity<>(paymentString, HttpStatus.OK);
    }

    @PutMapping("/payment-complete")
    public ResponseEntity<String> stripePaymentComplete(@RequestHeader(value = "Authorization") String token)
                                        throws Exception{

        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        if(userEmail == null){
            throw new Exception("User email is missing");
        }

        return paymentService.stripePayment(userEmail);
    }


}
