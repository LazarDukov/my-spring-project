package com.example.travelseeker.web;

import com.example.travelseeker.model.dtos.AddAirplaneTicketsDTO;
import com.example.travelseeker.model.dtos.AddCarsDTO;
import com.example.travelseeker.model.dtos.AddHotelsDTO;
import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.text.ParseException;

@Controller
@RequestMapping("/offers")
public class OfferController {


    private final AirplaneTicketsService airplaneTicketsService;


    private final HotelService hotelService;


    private final CarRentService carRentService;


    private final CartService cartService;

    private final UserService userService;

    @Autowired
    public OfferController(AirplaneTicketsService airplaneTicketsService, HotelService hotelService, CarRentService carRentService, CartService cartService, UserService userService) {

        this.airplaneTicketsService = airplaneTicketsService;

        this.hotelService = hotelService;

        this.carRentService = carRentService;


        this.cartService = cartService;

        this.userService = userService;
    }



    @GetMapping("/add-offer")
    public String addOffer() {
        return "add-offer";
    }




    // TODO: should redirect to right page after successful adding car and create restrictions in DTO



}
