package com.maxiflexy.springbootlibraryapp.controller;

import com.maxiflexy.springbootlibraryapp.payloads.request.AddBookRequest;
import com.maxiflexy.springbootlibraryapp.service.AdminService;
import com.maxiflexy.springbootlibraryapp.utils.ExtractJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/secure/add/book")
    public void postBook(@RequestHeader(value = "Authorization") String token,
                         @RequestBody AddBookRequest addBookRequest) throws Exception{

        String admin = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        String admin1 = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        String admin2 = ExtractJWT.payloadJWTExtraction(token, "\"email\"");
        System.out.println(admin); //rosemary@criticalthinkers.tech
        System.out.println(admin1); // null
        System.out.println(admin2); // null
//        if( admin == null || !admin.equals("admin")){
//            throw new Exception("Administration page only");
//        }
        adminService.postBook(addBookRequest);
    }

    @PutMapping("secure/increase/book/quantity")
    public void increaseBookQuantity(@RequestHeader(value = "Authorization") String token,
                                     @RequestParam Long bookId) throws Exception{

        String admin = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

        if(admin == null || !admin.equals("admin")){
            //rosemary@criticalthinkers.tech
            throw new Exception("Administration page only");
        }
        adminService.increaseBookQuantity(bookId);

    }
}
