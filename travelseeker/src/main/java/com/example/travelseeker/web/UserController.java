package com.example.travelseeker.web;


import com.example.travelseeker.model.dtos.view.UserProfileView;
import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.CarRent;
import com.example.travelseeker.model.entities.Hotel;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.service.BoughtOffersService;
import com.example.travelseeker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/users")
public class UserController {


    private final UserService userService;


    private final BoughtOffersService boughtOffersService;

    @Autowired
    public UserController(UserService userService, BoughtOffersService boughtOffersService) {
        this.userService = userService;

        this.boughtOffersService = boughtOffersService;
    }


    @GetMapping("/my-profile")
    private String getMyProfile(Model model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        UserProfileView userProfileView = new UserProfileView(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAge(), user.getRoleAsString(), user.getCountry());
        model.addAttribute("user", userProfileView);
        return "my-profile";
    }


    @GetMapping("/my-cart")
    public String getMyCart(Model model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        List<AirplaneTicket> airplaneTicketsInCart = new ArrayList<>(user.getCart().getAirplaneTickets());
        List<CarRent> carRentsInCart = new ArrayList<>(user.getCart().getCars());
        List<Hotel> hotelsInCart = new ArrayList<>(user.getCart().getHotels());
        model.addAttribute("myAirplaneTicketCart", airplaneTicketsInCart);
        //TODO: should implement the next two rows in the services and point them all by thymeleaf.
        model.addAttribute("myCarCart", carRentsInCart);
        model.addAttribute("myHotelCart", hotelsInCart);

        return "my-cart";
    }

    @GetMapping("/my-orders")
    public String getMyOrders(Model model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        List<AirplaneTicket> airplaneTicketsBought = new ArrayList<>(user.getBoughtOffers().getAirplaneTickets());
        List<CarRent> carRentsBought = new ArrayList<>(user.getBoughtOffers().getCars());
        List<Hotel> hotelsBought = new ArrayList<>(user.getBoughtOffers().getHotels());
        model.addAttribute("myAirplaneTicketBought", airplaneTicketsBought);
        //TODO: should implement the next two rows in the services and point them all by thymeleaf.
        model.addAttribute("myCarBought", carRentsBought);
        model.addAttribute("myHotelBought", hotelsBought);

        return "my-orders";
    }

    @PostMapping("/my-cart/buy-airplane-ticket-offer/{id}")
    public String buyAirplaneTicketFromCart(@PathVariable UUID id, Principal principal) {
        boughtOffersService.buyFromCart(id, principal);
        return "successfully-added";
    }

    @PostMapping("/my-cart/buy-hotel-offer/{id}")
    public String buyHotelFromCart(@PathVariable UUID id, Principal principal) {
        boughtOffersService.buyFromCart(id, principal);
        return "successfully-added";
    }

    @PostMapping("/my-cart/buy-car-offer/{id}")
    public String buyCarFromCart(@PathVariable UUID id, Principal principal) {
        boughtOffersService.buyFromCart(id, principal);
        return "successfully-added";
    }

}