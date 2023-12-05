package com.example.travelseeker.web;

import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.CarRent;
import com.example.travelseeker.model.entities.Hotel;
import com.example.travelseeker.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/buyers")
public class BuyerController {
    private final BuyerService buyerService;

    @Autowired
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @GetMapping("/cart")
    public String getMyCart(Model model, Principal principal) {
        Buyer buyer = buyerService.getBuyerByUsername(principal.getName());
        List<AirplaneTicket> airplaneTicketsInCart = new ArrayList<>(buyer.getCart().getAirplaneTickets());
        List<CarRent> carRentsInCart = new ArrayList<>(buyer.getCart().getCars());
        List<Hotel> hotelsInCart = new ArrayList<>(buyer.getCart().getHotels());
        model.addAttribute("myAirplaneTicketCart", airplaneTicketsInCart);
        //TODO: should implement the next two rows in the services and point them all by thymeleaf.
        model.addAttribute("myCarCart", carRentsInCart);
        model.addAttribute("myHotelCart", hotelsInCart);
        return "cart";
    }

    @GetMapping("/orders")
    public String getMyOrders(Principal principal, Model model) {
        List<AirplaneTicket> buyerAirplaneTickets = buyerService.getBuyerBoughtAirplaneTickets(principal);
        List<Hotel> buyerHotels = buyerService.getBuyerBoughtHotels(principal);
        List<CarRent> buyerCarRents = buyerService.getBuyerCarRents(principal);
        // Get all airplane tickets for the logged-in buyer from the bought offers

        model.addAttribute("myAirplaneTicketBought", buyerAirplaneTickets);
        model.addAttribute("myHotelBought", buyerHotels);
        model.addAttribute("myCarRentBought", buyerCarRents);
        // TODO: Implement similar logic for "myCarBought" and "myHotelBought"

        return "orders";
    }
}
