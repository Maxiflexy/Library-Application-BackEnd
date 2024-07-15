package com.maxiflexy.springbootlibraryapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "checkout")
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;
    private  String checkoutDate;
    private String returnDate;
    private Long bookId;


    public Checkout(String userEmail, String checkoutDate, String returnDate, Long bookId){
        this.userEmail = userEmail;
        this.checkoutDate = checkoutDate;
        this.returnDate= returnDate;
        this.bookId = bookId;
    }


}
