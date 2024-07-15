package com.maxiflexy.springbootlibraryapp.repository;

import com.maxiflexy.springbootlibraryapp.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

    Checkout findByUserEmailAndBookId(String userEmail, Long bookId);
}
