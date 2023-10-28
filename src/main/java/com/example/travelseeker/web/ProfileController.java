package com.example.travelseeker.web;


import com.example.travelseeker.model.dtos.view.UserProfileView;
import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.CarRent;
import com.example.travelseeker.model.entities.Hotel;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.service.AirplaneTicketsService;
import com.example.travelseeker.service.CarRentService;
import com.example.travelseeker.service.HotelService;
import com.example.travelseeker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ProfileController {

    private final UserService userService;

    private final AirplaneTicketsService airplaneTicketsService;
    private final CarRentService carRentService;
    private final HotelService hotelService;

    @Autowired
    public ProfileController(UserService userService, AirplaneTicketsService airplaneTicketsService, CarRentService carRentService, HotelService hotelService) {
        this.userService = userService;
        this.airplaneTicketsService = airplaneTicketsService;
        this.carRentService = carRentService;
        this.hotelService = hotelService;
    }



    @GetMapping("/users/my-profile")
    private String getMyProfile(Model model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        UserProfileView userProfileView = new UserProfileView(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAge(), user.getRoleAsString(), user.getCountry());
        model.addAttribute("user", userProfileView);
        return "my-profile";
    }


    @GetMapping("/users/my-cart")
    public String getMyOrders(Model model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        List<AirplaneTicket> airplaneTicketsOfUser = new ArrayList<>(user.getCart().getAirplaneTickets());
        List<CarRent> carRentsOfUser = new ArrayList<>(user.getCart().getCars());
        List<Hotel> hotelsOfUser = new ArrayList<>(user.getCart().getHotels());
        model.addAttribute("myAirplaneTicketOrders", airplaneTicketsOfUser);
        //TODO: should implement the next two rows in the services and point them all by thymeleaf.
        model.addAttribute("myCarOrders", carRentsOfUser);
        model.addAttribute("myHotelOrders", hotelsOfUser);

        return "my-cart";
    }

}
