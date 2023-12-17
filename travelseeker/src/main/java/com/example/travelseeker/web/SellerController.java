package com.example.travelseeker.web;

import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.CarRent;
import com.example.travelseeker.model.entities.Hotel;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.service.AirplaneTicketsService;
import com.example.travelseeker.service.CarRentService;
import com.example.travelseeker.service.HotelService;
import com.example.travelseeker.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/sellers")
public class SellerController {
    private final SellerService sellerService;
    private final AirplaneTicketsService airplaneTicketsService;
    private final HotelService hotelService;
    private final CarRentService carRentService;

    @Autowired
    public SellerController(SellerService sellerService, AirplaneTicketsService airplaneTicketsService, HotelService hotelService, CarRentService carRentService) {
        this.sellerService = sellerService;
        this.airplaneTicketsService = airplaneTicketsService;
        this.hotelService = hotelService;
        this.carRentService = carRentService;
    }

    @GetMapping("/published-offers/offers")
    public String getMyPublishedOffers(Model model, Principal principal) {
        List<AirplaneTicket> airplaneTicketsPublished =
                airplaneTicketsService.getAllAvailableAirplaneTicketsOfSeller(principal, 0);
        List<Hotel> hotelsPublished =
                hotelService.getAllAvailableHotelsOfSeller(principal, 0);
        List<CarRent> rentCarPublished =
                carRentService.getAllAvailableCarsRentOfSeller(principal, 0);
        model.addAttribute("myPublishedAirplaneTickets", airplaneTicketsPublished);
        model.addAttribute("myPublishedHotels", hotelsPublished);
        model.addAttribute("myPublishedCarRents", rentCarPublished);
        return "published-offers";
    }


    @GetMapping("/sold-offers/offers")
    public String getMySoldOffers(Principal principal, Model model) {
        Seller seller = sellerService.getSellerByUsername(principal.getName());
        List<AirplaneTicket> airplaneTicketsSold = sellerService.getSellerSoldAirplaneTickets(seller);
        List<Hotel> hotelsSold = sellerService.getSellerSoldHotels(seller);
        List<CarRent> carRentsSold = sellerService.getSellerSoldCarRents(seller);
        model.addAttribute("airplaneTicketsSold", airplaneTicketsSold);
        model.addAttribute("hotelsSold", hotelsSold);
        model.addAttribute("carRentsSold", carRentsSold);

        return "sold-offers";
    }
}
