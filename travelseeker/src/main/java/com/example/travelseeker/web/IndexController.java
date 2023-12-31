package com.example.travelseeker.web;

import com.example.travelseeker.service.AirplaneTicketsService;
import com.example.travelseeker.service.CarRentService;
import com.example.travelseeker.service.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final AirplaneTicketsService airplaneTicketsService;
    private final CarRentService carRentService;
    private final HotelService hotelService;

    public IndexController(AirplaneTicketsService airplaneTicketsService, CarRentService carRentService, HotelService hotelService) {

        this.airplaneTicketsService = airplaneTicketsService;

        this.carRentService = carRentService;
        this.hotelService = hotelService;
    }

    @GetMapping("/")
    public String getIndexPage(Model model) {

        return "index";
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
