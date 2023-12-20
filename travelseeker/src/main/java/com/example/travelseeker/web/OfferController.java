package com.example.travelseeker.web;

import com.example.travelseeker.service.OffersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OffersService offersService;

    public OfferController(OffersService offersService) {
        this.offersService = offersService;
    }

    @GetMapping("/add-offer")
    public String addOffer() {
        return "add-offer";
    }


    @GetMapping("/buy-airplane-ticket-offer/{id}")
    public String buyDirectlyAirplaneTicketFromCart(@PathVariable UUID id, Principal principal) {
        offersService.buyFromCartAirplaneTickets(id, principal);
        return "redirect:/orders/offers";
    }

    @GetMapping("/buy-hotel-offer/{id}")
    public String buyDirectlyHotelFromCart(@PathVariable UUID id, Principal principal) {
        offersService.buyFromCartHotels(id, principal);
        return "redirect:/orders/offers";
    }

    @GetMapping("/buy-car-offer/{id}")
    public String buyDirectlyCarRentFromCart(@PathVariable UUID id, Principal principal) {
        offersService.buyFromCartCarRents(id, principal);
        return "redirect:/orders/offers";
    }

}
