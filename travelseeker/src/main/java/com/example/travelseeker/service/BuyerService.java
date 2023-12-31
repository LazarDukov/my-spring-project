package com.example.travelseeker.service;

import com.example.travelseeker.exception.ObjectNotFoundException;
import com.example.travelseeker.model.entities.*;
import com.example.travelseeker.repository.BuyerRepository;
import com.example.travelseeker.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BuyerService {
    private final BuyerRepository buyerRepository;
    private final CartRepository cartRepository;


    public BuyerService(BuyerRepository buyerRepository, CartRepository cartRepository) {
        this.buyerRepository = buyerRepository;

        this.cartRepository = cartRepository;
    }

    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAll();

    }

    public Buyer getBuyerByUsername(String username) {
        return buyerRepository.findBuyerByUsername(username).orElseThrow(() -> new ObjectNotFoundException("Buyer with username " + username + " cannot be found!"));

    }

    public List<AirplaneTicket> getBuyerBoughtAirplaneTickets(Principal principal) {

        return getBuyerByUsername(principal.getName()).getBoughtOffers()
                .stream()
                .flatMap(offers -> offers.getAirplaneTickets().stream())
                .collect(Collectors.toList());
    }

    public List<CarRent> getBuyerCarRents(Principal principal) {
        return getBuyerByUsername(principal.getName()).getBoughtOffers()
                .stream()
                .flatMap(offer -> offer.getCarRents().stream())
                .collect(Collectors.toList());
    }

    public List<Hotel> getBuyerBoughtHotels(Principal principal) {

        return getBuyerByUsername(principal.getName()).getBoughtOffers()
                .stream()
                .flatMap(offer -> offer.getHotels().stream())
                .collect(Collectors.toList());
    }

    public void banBuyer(UUID id) {
        Buyer buyer = buyerRepository.findBuyerById(id);
        Cart cart = buyer.getCart();
        buyer.setCart(null);
        cart.setBuyer(null);
        buyerRepository.delete(buyer);
        cartRepository.delete(cart);
    }
}
