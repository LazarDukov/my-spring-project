package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.BoughtOffers;
import com.example.travelseeker.model.entities.Cart;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.repository.AirplaneTicketsRepository;
import com.example.travelseeker.repository.BoughtOffersRepository;
import com.example.travelseeker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;

@Service
public class BoughtOffersService {
    private final AirplaneTicketsRepository airplaneTicketsRepository;

    private final CartService cartService;
    private final UserRepository userRepository;

    private final BoughtOffersRepository boughtOffersRepository;

    @Autowired
    public BoughtOffersService(AirplaneTicketsRepository airplaneTicketsRepository, CartService cartService, UserRepository userRepository, BoughtOffersRepository boughtOffersRepository) {
        this.airplaneTicketsRepository = airplaneTicketsRepository;
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.boughtOffersRepository = boughtOffersRepository;
    }

    public BoughtOffers getNewBoughtOffers() {
        BoughtOffers newBoughtOffers = new BoughtOffers();
        boughtOffersRepository.saveAndFlush(newBoughtOffers);
        return newBoughtOffers;
    }

    public void buyFromCart(Principal principal) {
        User user = userRepository.findUserByUsername(principal.getName()).orElse(null);
        BoughtOffers boughtOffers = user.getBoughtOffers().setAirplaneTickets(user.getCart().getAirplaneTickets());
        user.setCart(new Cart());
        user.setBoughtOffers(boughtOffers);
        boughtOffersRepository.save(boughtOffers);
        userRepository.save(user);


    }
}
