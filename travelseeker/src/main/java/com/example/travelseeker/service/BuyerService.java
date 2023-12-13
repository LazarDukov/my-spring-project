package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.*;
import com.example.travelseeker.repository.BuyerRepository;
import com.example.travelseeker.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuyerService {
    private final BuyerRepository buyerRepository;


    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;

    }

    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAll();

    }
    public Buyer getBuyerByUsername(String username) {
        return buyerRepository.findBuyerByUsername(username).orElse(null);

    }

    public List<AirplaneTicket> getBuyerBoughtAirplaneTickets(Principal principal) {
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);
        assert buyer != null;
        return buyer.getBoughtOffers()
                .stream()
                .flatMap(offers -> offers.getAirplaneTickets().stream())
                .collect(Collectors.toList());
    }

    public List<CarRent> getBuyerCarRents(Principal principal) {
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);
        assert buyer != null;
        return buyer.getBoughtOffers()
                .stream()
                .flatMap(offer -> offer.getCarRents().stream())
                .collect(Collectors.toList());
    }

    public List<Hotel> getBuyerBoughtHotels(Principal principal) {
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);
        assert buyer != null;
        return buyer.getBoughtOffers()
                .stream()
                .flatMap(offer -> offer.getHotels().stream())
                .collect(Collectors.toList());
    }

}
