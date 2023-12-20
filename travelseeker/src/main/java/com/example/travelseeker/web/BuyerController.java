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

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class BuyerController {
    private final BuyerService buyerService;

    @Autowired
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @GetMapping("/cart/offers")
    public String getMyCart(Model model, Principal principal) {
        Buyer buyer = buyerService.getBuyerByUsername(principal.getName());
        List<AirplaneTicket> airplaneTicketsInCart = new ArrayList<>(buyer.getCart().getAirplaneTickets());
        List<CarRent> carRentsInCart = new ArrayList<>(buyer.getCart().getCars());
        List<Hotel> hotelsInCart = new ArrayList<>(buyer.getCart().getHotels());
        BigDecimal totalPrice = buyer.getCart().getTotalPrice();
        Map<UUID, Long> airplaneTicketsCountByBuyerId =
                airplaneTicketsInCart.stream().collect(Collectors.groupingBy(AirplaneTicket::getId, Collectors.counting()));
        Map<UUID, Long> carsCountByBuyerId
                = carRentsInCart.stream().collect(Collectors.groupingBy(CarRent::getId, Collectors.counting()));
        Map<UUID, Long> hotelsCountById =
                hotelsInCart.stream().collect(Collectors.groupingBy(Hotel::getId, Collectors.counting()));

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("myAirplaneTicketCart", airplaneTicketsInCart);
        model.addAttribute("airplaneTicketsCountById", airplaneTicketsCountByBuyerId);
        model.addAttribute("myCarCart", carRentsInCart);
        model.addAttribute("carRentsCountById", carsCountByBuyerId);
        model.addAttribute("myHotelCart", hotelsInCart);
        model.addAttribute("hotelsCountById", hotelsCountById);
        return "cart";
    }

    @GetMapping("/orders/offers")
    public String getMyOrders(Principal principal, Model model) {
        List<AirplaneTicket> buyerAirplaneTickets = buyerService.getBuyerBoughtAirplaneTickets(principal);
        List<Hotel> buyerHotels = buyerService.getBuyerBoughtHotels(principal);
        List<CarRent> buyerCarRents = buyerService.getBuyerCarRents(principal);
        Map<UUID, Long> airplaneTicketByBuyerId = buyerAirplaneTickets.stream().collect(Collectors.groupingBy(AirplaneTicket::getId, Collectors.counting()));
        Map<UUID, Long> carCountsByBuyerId = buyerCarRents.stream().collect(Collectors.groupingBy(CarRent::getId, Collectors.counting()));
        Map<UUID, Long> hotelCountsByBuyerId = buyerHotels.stream().collect(Collectors.groupingBy(Hotel::getId, Collectors.counting()));

        model.addAttribute("myAirplaneTicketBought", buyerAirplaneTickets);
        model.addAttribute("myHotelBought", buyerHotels);
        model.addAttribute("myCarRentBought", buyerCarRents);
        model.addAttribute("airplaneTicketDays", airplaneTicketByBuyerId);
        model.addAttribute("hotelDays", hotelCountsByBuyerId);
        model.addAttribute("carRentDays", carCountsByBuyerId);


        return "orders";
    }

}
