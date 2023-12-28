package com.example.travelseeker.web;

import com.example.travelseeker.service.CartService;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/cart/offers")
public class CartController {
    private final CartService cartService;


    public CartController(CartService cartService) {
        this.cartService = cartService;

    }


    @GetMapping("/add-airplane-ticket/{id}")
    public String addToCartAirplaneTicketOffer(@PathVariable UUID id, @RequestParam Integer days, Principal principal) {
        cartService.AddToCartAirplaneTicket(principal, id, days);

        return "redirect:/offers/airplane-tickets";
    }

    @GetMapping("/add-car/{id}")
    public String addToCartCarOffer(@PathVariable UUID id, @RequestParam Integer days, Principal principal) {
        cartService.AddToCartCar(principal, id, days);
        return "redirect:/offers/car-rents";
    }

    @GetMapping("/add-hotel/{id}")
    public String addToCartHotelOffer(@PathVariable UUID id, @RequestParam Integer days, Principal principal) {
        cartService.AddToCartHotel(principal, id, days);

        return "redirect:/offers/hotels";
    }

    @GetMapping("/remove-airplane-ticket-from-cart/{id}")
    public String removeFromCartAirplaneTicket(Principal principal, @PathVariable UUID id) {
        cartService.removeFromCartAirplaneTicket(principal, id);
        return "redirect:/cart/offers";
    }

    @GetMapping("/remove-car-rent-from-cart/{id}")
    public String removeFromCartCarRent(Principal principal, @PathVariable UUID id) {
        cartService.removeFromCartCarRent(principal, id);
        return "redirect:/cart/offers";
    }

    @GetMapping("/remove-hotel-from-cart/{id}")
    public String removeFromCartHotel(Principal principal, @PathVariable UUID id) {
        cartService.removeFromCartHotel(principal, id);
        return "redirect:/cart/offers";
    }


}
