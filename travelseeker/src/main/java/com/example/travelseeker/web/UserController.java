package com.example.travelseeker.web;


import com.example.travelseeker.model.dtos.EditProfileDTO;
import com.example.travelseeker.model.dtos.view.UserProfileView;
import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.CarRent;
import com.example.travelseeker.model.entities.Hotel;
import com.example.travelseeker.repository.HotelRepository;
import com.example.travelseeker.service.*;
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

    private final SellerService sellerService;
    private final BuyerService buyerService;
    private final UserService userService;


    private final OffersService offersService;
    private final AirplaneTicketsService airplaneTicketsService;
    private final HotelService hotelService;
    private final CarRentService carRentService;

    @Autowired
    public UserController(SellerService sellerService, BuyerService buyerService,
                          UserService userService, OffersService offersService, HotelRepository hotelRepository, AirplaneTicketsService airplaneTicketsService, HotelService hotelService, CarRentService carRentService) {
        this.sellerService = sellerService;
        this.buyerService = buyerService;
        this.userService = userService;

        this.offersService = offersService;
        this.airplaneTicketsService = airplaneTicketsService;

        this.hotelService = hotelService;

        this.carRentService = carRentService;
    }


    @ModelAttribute("editProfileDTO")
    public EditProfileDTO editProfileDTO() {
        return new EditProfileDTO();
    }

    @GetMapping("/my-profile")
    public String getMyProfile(Model model, Principal principal) {
        UserProfileView userProfileView = userService.getUserProfileView(principal);
        model.addAttribute("user", userProfileView);
        return "my-profile";
    }


    @GetMapping("/edit-profile")
    private String getEditProfile(Model model, Principal principal) {
        UserProfileView userProfileView = userService.getUserProfileView(principal);
        model.addAttribute("user", userProfileView);
        return "edit-profile";
    }

    // TODO: should repair showing of role not to be optional and given as a string
    @PostMapping("/edit-profile")
    public String updateUserProfile(EditProfileDTO editProfileDTO, Principal principal, BindingResult
            bindingResult, RedirectAttributes redirectAttributes) {
        userService.editProfile(editProfileDTO, principal);


        return "redirect:/users/my-profile";
    }

    @GetMapping("/my-cart")
    public String getMyCart(Model model, Principal principal) {
        Buyer buyer = buyerService.getBuyerByUsername(principal.getName());
        List<AirplaneTicket> airplaneTicketsInCart = new ArrayList<>(buyer.getCart().getAirplaneTickets());
        List<CarRent> carRentsInCart = new ArrayList<>(buyer.getCart().getCars());
        List<Hotel> hotelsInCart = new ArrayList<>(buyer.getCart().getHotels());
        model.addAttribute("myAirplaneTicketCart", airplaneTicketsInCart);
        //TODO: should implement the next two rows in the services and point them all by thymeleaf.
        model.addAttribute("myCarCart", carRentsInCart);
        model.addAttribute("myHotelCart", hotelsInCart);
        return "my-cart";
    }

    @GetMapping("/my-orders")
    public String getMyOrders(Principal principal, Model model) {
        List<AirplaneTicket> buyerAirplaneTickets = buyerService.getBuyerBoughtAirplaneTickets(principal);
        List<Hotel> buyerHotels = buyerService.getBuyerBoughtHotels(principal);
        List<CarRent> buyerCarRents = buyerService.getBuyerCarRents(principal);
        // Get all airplane tickets for the logged-in buyer from the bought offers

        model.addAttribute("myAirplaneTicketBought", buyerAirplaneTickets);
        model.addAttribute("myHotelBought", buyerHotels);
        model.addAttribute("myCarRentBought", buyerCarRents);
        // TODO: Implement similar logic for "myCarBought" and "myHotelBought"

        return "my-orders";
    }

    @PostMapping("/my-cart/buy-airplane-ticket-offer/{id}")
    public String buyAirplaneTicketFromCart(@PathVariable UUID id, Principal principal) {
        offersService.buyFromCartAirplaneTickets(id, principal);
        return "successfully-added";
    }

    @PostMapping("/my-cart/buy-hotel-offer/{id}")
    public String buyHotelFromCart(@PathVariable UUID id, Principal principal) {
        offersService.buyFromCartHotels(id, principal);
        return "successfully-added";
    }

    @PostMapping("/my-cart/buy-car-offer/{id}")
    public String buyCarRentFromCart(@PathVariable UUID id, Principal principal) {
        offersService.buyFromCartCarRents(id, principal);
        return "successfully-added";
    }

}



