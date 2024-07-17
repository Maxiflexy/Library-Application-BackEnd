package com.maxiflexy.springbootlibraryapp.repository;

import com.maxiflexy.springbootlibraryapp.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByUserEmail(String userEmail);
}
