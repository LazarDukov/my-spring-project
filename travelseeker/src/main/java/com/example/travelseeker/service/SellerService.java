package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.CarRent;
import com.example.travelseeker.model.entities.Hotel;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
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
}
