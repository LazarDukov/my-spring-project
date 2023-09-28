package com.example.travelseeker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
    @GetMapping("/book-now")
    public String getBookNow() {
        return "book-now";
    }
}
