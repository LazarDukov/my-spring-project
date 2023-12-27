package com.example.travelseeker.service;

import com.example.travelseeker.exception.ObjectNotFoundException;
import com.example.travelseeker.model.entities.*;
import com.example.travelseeker.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;
    private final OffersRepository offersRepository;
    private final AirplaneTicketsRepository airplaneTicketsRepository;
    private final CarRentRepository carRentRepository;
    private final HotelRepository hotelRepository;

    public SellerService(SellerRepository sellerRepository, OffersRepository offersRepository, AirplaneTicketsRepository airplaneTicketsRepository, CarRentRepository carRentRepository, HotelRepository hotelRepository) {
        this.sellerRepository = sellerRepository;
        this.offersRepository = offersRepository;
        this.airplaneTicketsRepository = airplaneTicketsRepository;
        this.carRentRepository = carRentRepository;
        this.hotelRepository = hotelRepository;
    }

    public Seller getSellerByUsername(String username) {
        return sellerRepository.findSellerByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User with username " + username + " cannot be found!"));

    }


    public List<AirplaneTicket> getSellerSoldAirplaneTickets(Seller seller) {
        return seller.getSealedOffers().stream()
                .flatMap(offer -> offer.getAirplaneTickets()
                        .stream())
                .collect(Collectors.toList());

    }

    public List<Hotel> getSellerSoldHotels(Seller seller) {
        return seller.getSealedOffers().stream()
                .flatMap(offer -> offer.getHotels()
                        .stream())
                .collect(Collectors.toList());
    }

    public List<CarRent> getSellerSoldCarRents(Seller seller) {
        return seller.getSealedOffers().stream()
                .flatMap(offer -> offer.getCarRents()
                        .stream())
                .collect(Collectors.toList());
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();

    }

    public void banSeller(UUID id) {
        Seller seller = sellerRepository.findSellerById(id).orElse(null);
        List<Offers> offers = offersRepository.findOffersBySellerId(id);
        List<AirplaneTicket> airplaneTickets = airplaneTicketsRepository.findAirplaneTicketsBySellerId(id);
        List<CarRent> carRents = carRentRepository.findCarRentsBySellerId(id);
        List<Hotel> hotels = hotelRepository.findHotelsBySellerId(id);

        seller.setSealedOffers(new ArrayList<>());
        for (AirplaneTicket a : airplaneTickets) {
            a.setSeller(null);
        }
        for (Hotel h : hotels) {
            h.setSeller(null);
        }
        for (CarRent c : carRents) {
            c.setSeller(null);
        }
        for (Offers o : offers) {
            o.setSeller(null);
        }
        offersRepository.deleteAll(offers);

        airplaneTicketsRepository.deleteAll(airplaneTickets);
        carRentRepository.deleteAll(carRents);
        hotelRepository.deleteAll(hotels);
        sellerRepository.delete(seller);
    }
}
