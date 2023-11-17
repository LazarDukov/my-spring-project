package com.example.travelseeker.web;


import com.example.travelseeker.model.dtos.EditProfileDTO;
import com.example.travelseeker.model.dtos.UserRegistrationDTO;
import com.example.travelseeker.model.dtos.view.UserProfileView;
import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.CarRent;
import com.example.travelseeker.model.entities.Hotel;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.service.BoughtOffersService;
import com.example.travelseeker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @ModelAttribute("editProfileDTO")
    public EditProfileDTO editProfileDTO() {
        return new EditProfileDTO();
    }

    @GetMapping("/my-profile")
    private String getMyProfile(Model model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        UserProfileView userProfileView = new UserProfileView(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAge(), user.getRoleAsString(), user.getCountry());
        model.addAttribute("user", userProfileView);
        return "my-profile";
    }

    @GetMapping("/edit-profile")
    private String getEditProfile(Model model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        UserProfileView userProfileView = new UserProfileView(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAge(), user.getRoleAsString(), user.getCountry());
        model.addAttribute("user", userProfileView);
        return "edit-profile";
    }

    // TODO: should repair showing of role not to be optional and given as a string
    @PostMapping("/edit-profile")
    public String updateUserProfile(EditProfileDTO editProfileDTO, Principal principal, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        userService.editProfile(editProfileDTO, principal);


        return "redirect:/users/my-profile";
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
