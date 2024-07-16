package com.maxiflexy.springbootlibraryapp.service.impl;

import com.maxiflexy.springbootlibraryapp.entity.Book;
import com.maxiflexy.springbootlibraryapp.entity.Checkout;
import com.maxiflexy.springbootlibraryapp.payloads.response.ShelfCurrentLoansResponse;
import com.maxiflexy.springbootlibraryapp.repository.BookRepository;
import com.maxiflexy.springbootlibraryapp.repository.CheckoutRepository;
import com.maxiflexy.springbootlibraryapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CheckoutRepository checkoutRepository;


    public Book checkoutBook(String userEmail, Long bookId) throws Exception{

        Optional<Book> book = bookRepository.findById(bookId);

        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);

        if(!book.isPresent() || validateCheckout != null || book.get().getCopiesAvailable() <= 0){
            throw new Exception("Book does not exist or already checked out by the user");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
        bookRepository.save(book.get());

        Checkout checkout = new Checkout(
                userEmail, LocalDate.now().toString(), LocalDate.now().plusDays(7).toString(), book.get().getId());

        checkoutRepository.save(checkout);

        return book.get();

    }

    public boolean checkoutBookByUser(String userEmail, Long bookId){
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);

        if( validateCheckout != null)
            return true;
        else
            return false;
    }

    public int currentLoansCount(String userEmail){
        return checkoutRepository.findBooksByUserEmail(userEmail).size();
    }

    public List<ShelfCurrentLoansResponse> currentLoans(String userEmail) throws Exception{

        List<ShelfCurrentLoansResponse> shelfCurrentLoansResponses = new ArrayList<>();

        List<Checkout> checkoutList = checkoutRepository.findBooksByUserEmail(userEmail);
        List<Long> bookIdList = new ArrayList<>();

        for(Checkout i : checkoutList){
            bookIdList.add(i.getBookId());
        }

        List<Book> books = bookRepository.findBooksByBookIds(bookIdList);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for(Book book : books){
            Optional<Checkout> checkout = checkoutList.stream().filter(
                    x -> x.getBookId() == book.getId()).findFirst();

            if(checkout.isPresent()){
                Date d1 = simpleDateFormat.parse(checkout.get().getReturnDate());
                Date d2 = simpleDateFormat.parse(LocalDate.now().toString());

                TimeUnit time = TimeUnit.DAYS;

                long difference_In_Time = time.convert(
                        d1.getTime() - d2.getTime(), TimeUnit.MILLISECONDS);

                shelfCurrentLoansResponses.add(new ShelfCurrentLoansResponse(book, (int) difference_In_Time));
            }
        }
        return shelfCurrentLoansResponses;
    }
}
