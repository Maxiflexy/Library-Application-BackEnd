package com.maxiflexy.springbootlibraryapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    private int copies;

    private int copiesAvailable;

    private String category;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String img;
}
