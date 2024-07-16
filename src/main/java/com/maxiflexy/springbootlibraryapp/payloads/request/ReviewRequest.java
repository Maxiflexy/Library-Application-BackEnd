package com.maxiflexy.springbootlibraryapp.payloads.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {

    private double rating;
    private Long bookId;
    private Optional<String> reviewDescription;
}
