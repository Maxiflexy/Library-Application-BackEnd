package com.maxiflexy.springbootlibraryapp.repository;

import com.maxiflexy.springbootlibraryapp.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
