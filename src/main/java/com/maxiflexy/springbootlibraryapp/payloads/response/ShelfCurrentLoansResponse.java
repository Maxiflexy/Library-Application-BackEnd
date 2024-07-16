package com.maxiflexy.springbootlibraryapp.payloads.response;

import com.maxiflexy.springbootlibraryapp.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShelfCurrentLoansResponse {

    private Book book;
    private int daysLeft;

}
