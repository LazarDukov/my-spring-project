package com.example.travelseeker.web;

import com.example.travelseeker.service.AirplaneTicketsService;
import com.example.travelseeker.service.CarRentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private final AirplaneTicketsService airplaneTicketsService;
    private final CarRentService carRentService;

    public IndexController(AirplaneTicketsService airplaneTicketsService, CarRentService carRentService) {
        this.airplaneTicketsService = airplaneTicketsService;

        this.carRentService = carRentService;
    }

    @GetMapping("/")
    public String getIndexPage(Model model) {

        model.addAttribute("indexAirplaneTickets", this.airplaneTicketsService.getAllAirplaneTickets());
        model.addAttribute("indexCars", this.carRentService.getAllCars());
        model.addAttribute("indexHotels", this.carRentService.getAllCars());

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
