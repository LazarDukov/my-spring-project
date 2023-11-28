package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.Offers;
import com.example.travelseeker.repository.AirplaneTicketsRepository;
import com.example.travelseeker.repository.BuyerRepository;
import com.example.travelseeker.repository.OffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OffersService {
    private final AirplaneTicketsRepository airplaneTicketsRepository;
    private final OffersRepository offersRepository;
    private final BuyerRepository buyerRepository;

    @Autowired
    public OffersService(AirplaneTicketsRepository airplaneTicketsRepository, OffersRepository offersRepository, BuyerRepository buyerRepository) {

        this.airplaneTicketsRepository = airplaneTicketsRepository;

        this.offersRepository = offersRepository;
        this.buyerRepository = buyerRepository;
    }


    public void buyFromCart(UUID id, Principal principal) {
        // Find the buyer by username
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);

        if (buyer != null) {
            // Find the airplane ticket to buy
            AirplaneTicket airplaneTicketToBuy = airplaneTicketsRepository.findAirplaneTicketById(id);
            // create new instance of Offers
            Offers offer = new Offers();
            // adds offer in Offers -> airplane tickets
            offer.getAirplaneTickets().add(airplaneTicketToBuy);
            // adds airplane ticket to bought offers of Buyer
            buyer.getBoughtOffers().add(offer);
            // remove airplane ticket from cart
            buyer.getCart().getAirplaneTickets().remove(airplaneTicketToBuy);
            // saving changes in offers with new offer - airplane ticket
            offersRepository.save(offer);
            // saving changes in buyer with new added offers of airplane ticket
            buyerRepository.save(buyer);
        }
    }


    public List<AirplaneTicket> getBuyerBoughtAirplaneTickets(Principal principal) {
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);
        assert buyer != null;
        return buyer.getBoughtOffers()
                .stream()
                .flatMap(offers -> offers.getAirplaneTickets().stream())
                .collect(Collectors.toList());
    }
}

