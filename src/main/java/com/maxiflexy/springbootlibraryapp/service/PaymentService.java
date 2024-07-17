package com.maxiflexy.springbootlibraryapp.service;

import com.maxiflexy.springbootlibraryapp.payloads.request.PaymentInfoRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.http.ResponseEntity;

public interface PaymentService {

    PaymentIntent createPaymentIntent(PaymentInfoRequest paymentInfoRequest) throws StripeException;

    ResponseEntity<String> stripePayment(String userEmail) throws Exception;
}
