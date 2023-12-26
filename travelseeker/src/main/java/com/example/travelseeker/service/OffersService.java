package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.*;
import com.example.travelseeker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
public class OffersService {
    private final AirplaneTicketsRepository airplaneTicketsRepository;
    private final HotelRepository hotelRepository;
    private final CarRentRepository carRentRepository;
    private final OffersRepository offersRepository;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;

    @Autowired
    public OffersService(AirplaneTicketsRepository airplaneTicketsRepository, HotelRepository hotelRepository, CarRentRepository carRentRepository, OffersRepository offersRepository, BuyerRepository buyerRepository, SellerRepository sellerRepository) {

        this.airplaneTicketsRepository = airplaneTicketsRepository;
        this.hotelRepository = hotelRepository;
        this.carRentRepository = carRentRepository;

        this.offersRepository = offersRepository;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
    }

    private Buyer getBuyer(Principal principal) {
        return buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);
    }

    public void buyFromCartAirplaneTickets(UUID id, Principal principal) {

        Buyer buyer = getBuyer(principal);
        if (buyer != null) {
            AirplaneTicket airplaneTicket = airplaneTicketsRepository.findAirplaneTicketById(id);
            Offers offer = offersRepository.findByAirplaneTicketId(airplaneTicket.getId());

            List<AirplaneTicket> airplaneTicketsInThisCart = buyer.getCart().getAirplaneTickets().stream()
                    .filter(at -> at.getId().equals(airplaneTicket.getId())).toList();
            int numberOfTickets = airplaneTicketsInThisCart.size();
            airplaneTicket.setAvailable(airplaneTicket.getAvailable() - numberOfTickets);
            airplaneTicket.setSoldNumber(airplaneTicket.getSoldNumber() + numberOfTickets);
            for (int i = 0; i < numberOfTickets; i++) {
                buyer.getBoughtOffers().add(offer);
                buyer.getCart().getAirplaneTickets().remove(airplaneTicket);
            }
            Seller seller = airplaneTicket.getSeller();
            if (seller.getSealedOffers().stream().noneMatch(o -> o.getAirplaneTickets().contains(airplaneTicket))) {
                seller.getSealedOffers().add(offer);
                sellerRepository.save(seller);
            }
            buyer.getCart().setTotalPrice(buyer.getCart().getTotalPrice().subtract(airplaneTicket.getPrice().multiply(BigDecimal.valueOf(numberOfTickets))));
            buyerRepository.save(buyer);


        }
    }

    public void buyFromCartHotels(UUID id, Principal principal) {

        Buyer buyer = getBuyer(principal);

        if (buyer != null) {

            Hotel hotel = hotelRepository.findHotelById(id);

            Offers offer = offersRepository.findByHotelId(hotel.getId());
            List<Hotel> hotelsInThisCart = buyer.getCart().getHotels().stream()
                    .filter(h -> h.getId().equals(hotel.getId())).toList();
            int numberOfNights = hotelsInThisCart.size();
            hotel.setAvailable(hotel.getAvailable() - numberOfNights);
            hotel.setSoldNumber(hotel.getSoldNumber() + numberOfNights);
            Seller seller = hotel.getSeller();
            for (int i = 0; i < numberOfNights; i++) {
                buyer.getBoughtOffers().add(offer);
                buyer.getCart().getHotels().remove(hotel);
            }
            if (seller.getSealedOffers().stream().noneMatch(o -> o.getHotels().contains(hotel))) {
                seller.getSealedOffers().add(offer);
                sellerRepository.save(seller);
            }
            buyer.getCart().setTotalPrice(buyer.getCart().getTotalPrice().subtract(hotel.getPricePerNight().multiply(BigDecimal.valueOf(numberOfNights))));
            buyerRepository.save(buyer);

        }
    }

    public void buyFromCartCarRents(UUID id, Principal principal) {
        Buyer buyer = getBuyer(principal);
        if (buyer != null) {
            CarRent car = carRentRepository.findCarRentById(id);
            Offers offer = offersRepository.findByCarRentId(car.getId());

            List<CarRent> carRentsInThisCart = buyer.getCart().getCars().stream()
                    .filter(carRent -> carRent.getId().equals(car.getId())).toList();
            int numberCarRents = carRentsInThisCart.size();
            car.setAvailable(car.getAvailable() - numberCarRents);
            car.setSoldNumber(car.getSoldNumber() + numberCarRents);
            Seller seller = car.getSeller();
            for (int i = 0; i < numberCarRents; i++) {
                buyer.getBoughtOffers().add(offer);
                buyer.getCart().getCars().remove(car);
            }
            if (seller.getSealedOffers().stream().noneMatch(o -> o.getCarRents().contains(car))) {
                seller.getSealedOffers().add(offer);
                sellerRepository.save(seller);
            }
            buyer.getCart().setTotalPrice(buyer.getCart().getTotalPrice().subtract(car.getPrice().multiply(BigDecimal.valueOf(numberCarRents))));
            buyerRepository.save(buyer);
        }
    }


}

