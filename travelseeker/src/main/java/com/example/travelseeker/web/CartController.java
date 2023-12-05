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

        return "successfully-added";
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

    @PostMapping("/cart/remove-airplane-ticket-from-cart/{id}")
    public String removeFromCartAirplaneTicket(Principal principal, @PathVariable UUID id) {
        cartService.removeFromCartAirplaneTicket(principal, id);
        return "redirect:/users/cart";
    }

    @PostMapping("/cart/remove-car-from-cart/{id}")
    public String removeFromCartCarRent(Principal principal, @PathVariable UUID id) {
        cartService.removeFromCartCarRent(principal, id);
        return "redirect:/users/cart";
    }
    @PostMapping("/cart/remove-hotel-from-cart/{id}")
    public String removeFromCartHotel(Principal principal, @PathVariable UUID id) {
        cartService.removeFromCartHotel(principal, id);
        return "redirect:/users/cart";
    }


}
