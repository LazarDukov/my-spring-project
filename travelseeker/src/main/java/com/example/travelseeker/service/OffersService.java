package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.Offers;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.repository.AirplaneTicketsRepository;
import com.example.travelseeker.repository.OffersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OffersService {
    private final AirplaneTicketsRepository airplaneTicketsRepository;
    private final OffersRepository offersRepository;

    @Autowired
    public OffersService(OffersRepository offersRepository, AirplaneTicketsRepository airplaneTicketsRepository, OffersRepository offersRepository1) {

        this.airplaneTicketsRepository = airplaneTicketsRepository;

        this.offersRepository = offersRepository1;
    }

//    public List<AirplaneTicket> getAirplaneTicketsOffers(Principal principal) {
//        User user = userRepository.findUserByUsername(principal.getName()).orElse(null);
//
//        return airplaneTicketsRepository.findAirplaneTicketByUsersId(user.getId());
//
//    }

//    public void buyFromCart(UUID id, Principal principal) {
//        User user = userRepository.findUserByUsername(principal.getName()).orElse(null);
//        AirplaneTicket airplaneTicketToBuy = airplaneTicketsRepository.findAirplaneTicketById(id);
//        List<AirplaneTicket> usersAirplaneTicketsBought =
//                user.getOffers().stream().findFirst().map(Offers::getAirplaneTickets).orElse(new ArrayList<>());
//        usersAirplaneTicketsBought.add(airplaneTicketToBuy);
//        user.getCart().getAirplaneTickets().remove(airplaneTicketToBuy);
//        airplaneTicketToBuy.getUsers().add(user);
//        userRepository.save(user);
//
//    }
}

