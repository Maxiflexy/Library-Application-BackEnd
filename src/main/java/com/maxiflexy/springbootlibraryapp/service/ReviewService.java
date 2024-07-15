package com.maxiflexy.springbootlibraryapp.service;

import com.maxiflexy.springbootlibraryapp.payloads.request.ReviewRequest;

public interface ReviewService {

    void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception;
}
