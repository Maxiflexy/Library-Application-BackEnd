package com.maxiflexy.springbootlibraryapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "checkout_date")
    private String checkoutDate;

    @Column(name = "returned_date")
    private String returnedDate;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    public History(String userEmail, String checkoutDate, String returnedDate,
                   String title, String author, String description, String img) {
        this.author = author;
        this.checkoutDate = checkoutDate;
        this.description = description;
        this.image = img;
        this.returnedDate = returnedDate;
        this.title = title;
        this.userEmail = userEmail;
    }
}
