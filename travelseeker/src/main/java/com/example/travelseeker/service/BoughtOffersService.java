package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.BoughtOffers;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.repository.AirplaneTicketsRepository;
import com.example.travelseeker.repository.BoughtOffersRepository;
import com.example.travelseeker.repository.CartRepository;
import com.example.travelseeker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
public class BoughtOffersService {
    private final AirplaneTicketsRepository airplaneTicketsRepository;

    private final CartService cartService;
    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    private final BoughtOffersRepository boughtOffersRepository;

    @Autowired
    public BoughtOffersService(AirplaneTicketsRepository airplaneTicketsRepository, CartService cartService, UserRepository userRepository, CartRepository cartRepository, BoughtOffersRepository boughtOffersRepository) {
        this.airplaneTicketsRepository = airplaneTicketsRepository;
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.boughtOffersRepository = boughtOffersRepository;
    }

    public BoughtOffers getNewBoughtOffers() {
        BoughtOffers newBoughtOffers = new BoughtOffers();
        boughtOffersRepository.saveAndFlush(newBoughtOffers);
        return newBoughtOffers;
    }

    public void buyFromCart(UUID id, Principal principal) {
        User user = userRepository.findUserByUsername(principal.getName()).orElse(null);
        AirplaneTicket airplaneTicketToBuy = airplaneTicketsRepository.findAirplaneTicketById(id);
        BoughtOffers boughtOffers = user.getBoughtOffers();
        List<AirplaneTicket> boughtAirplaneTicketsOffersOfUser = user.getBoughtOffers().getAirplaneTickets();
        boughtAirplaneTicketsOffersOfUser.add(airplaneTicketToBuy);
        user.getCart().getAirplaneTickets().remove(airplaneTicketToBuy);
        boughtOffers.setUser(user);
        userRepository.save(user);
        boughtOffersRepository.save(boughtOffers);


    }
}
