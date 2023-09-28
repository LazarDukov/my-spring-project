package com.example.travelseeker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OfferController {
    @GetMapping("/offers/airplane-tickets")
    public String getAirplaneTicket() {
        return "airplane-tickets";
    }

    @GetMapping("/offers/cars")
    public String getCars() {
        return "cars";
    }

    @GetMapping("/offers/hotels")
    public String getHotels() {
        return "hotels";
    }
}
