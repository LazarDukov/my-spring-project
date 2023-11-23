package com.example.travelseeker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OfferController {

    @GetMapping("/book-now")
    public String getBookNow() {
        return "book-now";
    }

    @GetMapping("/add-offer")
    public String addOffer() {
        return "add-offer";
    }


    // TODO: should redirect to right page after successful adding car and create restrictions in DTO


}
