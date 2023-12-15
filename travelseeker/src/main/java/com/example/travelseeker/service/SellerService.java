package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.*;
import com.example.travelseeker.repository.AirplaneTicketsRepository;
import com.example.travelseeker.repository.OffersRepository;
import com.example.travelseeker.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;
    private final OffersRepository offersRepository;
    private final AirplaneTicketsRepository airplaneTicketsRepository;

    public SellerService(SellerRepository sellerRepository, OffersRepository offersRepository, AirplaneTicketsRepository airplaneTicketsRepository) {
        this.sellerRepository = sellerRepository;
        this.offersRepository = offersRepository;
        this.airplaneTicketsRepository = airplaneTicketsRepository;
    }

    public Seller getSellerByUsername(String username) {
        return sellerRepository.findSellerByUsername(username).orElse(null);

    }


    public List<AirplaneTicket> getSellerSoldAirplaneTickets(Seller seller) {
        return seller.getSealedOffers().stream().flatMap(offer -> offer.getAirplaneTickets().stream()).collect(Collectors.toList());

    }

    public List<Hotel> getSellerSoldHotels(Seller seller) {
        return seller.getSealedOffers().stream().flatMap(offer -> offer.getHotels().stream()).collect(Collectors.toList());
    }

    public List<CarRent> getSellerSoldCarRents(Seller seller) {
        return seller.getSealedOffers().stream().flatMap(offer -> offer.getCarRents().stream()).collect(Collectors.toList());
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();

    }

    public void banSeller(UUID id) {
        Seller seller = sellerRepository.findSellerById(id);
        List<Offers> offers = offersRepository.findOffersBySellerId(id);
        List<AirplaneTicket> airplaneTickets = airplaneTicketsRepository.findAirplaneTicketsBySellerId(id);
        for (AirplaneTicket a : airplaneTickets) {
            a.setSeller(null);
        }
        for (Offers o : offers) {
            o.setSeller(null);
        }
        offersRepository.deleteAll(offers);
        airplaneTicketsRepository.deleteAll(airplaneTickets);
        sellerRepository.delete(seller);
    }
}
