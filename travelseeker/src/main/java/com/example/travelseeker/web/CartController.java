package com.example.travelseeker.web;

import com.example.travelseeker.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/view-airplane-ticket-offer/{id}/addToCart")
    public String addToCartAirplaneTicketOffer
            (@PathVariable UUID id, Principal principal) {
        cartService.AddToCartAirplaneTicket(principal, id);

        return "redirect:/offers/airplane-tickets";
    }

    @GetMapping("/view-car-offer/{id}/addToCart")
    public String addToCartCarOffer(@PathVariable UUID id, Principal principal) {
        cartService.AddToCartCar(principal, id);
        return "successfully-added";
    }

    @GetMapping("/view-hotel-offer/{id}/addToCart")
    public String addToCartHotelOffer(@PathVariable UUID id, Principal principal) {
        cartService.AddToCartHotel(principal, id);

        return "successfully-added";
    }




}
