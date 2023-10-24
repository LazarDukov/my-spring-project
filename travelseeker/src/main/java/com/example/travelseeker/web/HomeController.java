package com.example.travelseeker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/offers/book-now")
    public String getBookNow() {
        return "book-now";
    }

    @GetMapping("/about-us")
    public String getAboutUs() {
        return "about-us";
    }

    @GetMapping("/contacts")
    public String getContacts() {
        return "contacts";
    }




}
