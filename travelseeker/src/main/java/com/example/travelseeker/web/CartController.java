package com.example.travelseeker.web;

import com.example.travelseeker.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/read-airplane-ticket-offer/{id}/addToCart")
    public String addToCartAirplaneTicketOffer
            (@PathVariable UUID id,
             @RequestParam(name = "myCheckbox",
                     required = false) boolean myCheckbox, Principal principal) {
        cartService.AddToCartAirplaneTicket(myCheckbox, principal, id);

        return "successfully-added";
    }

    @GetMapping("/read-car-offer/{id}/addToCart")
    public String addToCartCarOffer(@PathVariable UUID id, Principal principal) {
        cartService.AddToCartCar(principal, id);
        return "successfully-added";
    }

    @GetMapping("/read-hotel-offer/{id}/addToCart")
    public String addToCartHotelOffer(@PathVariable UUID id, Principal principal) {
        cartService.AddToCartHotel(principal, id);

        return "successfully-added";
    }

}
