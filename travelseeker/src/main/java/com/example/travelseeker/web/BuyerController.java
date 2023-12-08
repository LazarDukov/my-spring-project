package com.example.travelseeker.web;

import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.CarRent;
import com.example.travelseeker.model.entities.Hotel;
import com.example.travelseeker.service.BuyerService;
import com.example.travelseeker.service.CartService;
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
@RequestMapping("/buyers")
public class BuyerController {
    private final BuyerService buyerService;
    private final CartService cartService;

    @Autowired
    public BuyerController(BuyerService buyerService, CartService cartService) {
        this.buyerService = buyerService;
        this.cartService = cartService;
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


        model.addAttribute("myAirplaneTicketBought", buyerAirplaneTickets);
        model.addAttribute("myHotelBought", buyerHotels);
        model.addAttribute("myCarRentBought", buyerCarRents);


        return "orders";
    }
    @GetMapping("/cart/remove-airplane-ticket-from-cart/{id}")
    public String removeFromCartAirplaneTicket(Principal principal, @PathVariable UUID id) {
        cartService.removeFromCartAirplaneTicket(principal, id);
        return "redirect:/buyers/cart";
    }

    @GetMapping("/cart/remove-car-from-cart/{id}")
    public String removeFromCartCarRent(Principal principal, @PathVariable UUID id) {
        cartService.removeFromCartCarRent(principal, id);
        return "redirect:/buyers/cart";
    }
    @GetMapping("/cart/remove-hotel-from-cart/{id}")
    public String removeFromCartHotel(Principal principal, @PathVariable UUID id) {
        cartService.removeFromCartHotel(principal, id);
        return "redirect:/buyers/cart";
    }
}
