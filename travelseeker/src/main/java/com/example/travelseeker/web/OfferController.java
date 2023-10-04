package com.example.travelseeker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OfferController {
    @GetMapping("/add-offer")
    public String addOffer() {
        return "add-offer";
    }

    @GetMapping("/airplane-tickets")
    public String getAirplaneTicket() {
        return "airplane-tickets";
    }

    @GetMapping("/cars")
    public String getCars() {
        return "cars";
    }

    @GetMapping("/hotels")
    public String getHotels() {
        return "hotels";
    }

}
