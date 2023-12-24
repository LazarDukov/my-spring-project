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


    public void buyFromCartAirplaneTickets(UUID id, Principal principal) {

        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);
        if (buyer != null) {

            AirplaneTicket airplaneTicketToBuy = airplaneTicketsRepository.findAirplaneTicketById(id);

            Seller seller = airplaneTicketToBuy.getSeller();

            Offers offer = offersRepository.findByAirplaneTicketId(airplaneTicketToBuy.getId());


            airplaneTicketToBuy.setAvailable(airplaneTicketToBuy.getAvailable() - 1);

            airplaneTicketToBuy.setSoldNumber(airplaneTicketToBuy.getSoldNumber() + 1);
            if (!sellerRepository.existsById(seller.getId())) {


                seller.getSealedOffers().add(offer);

                sellerRepository.save(seller);
            }

            buyer.getBoughtOffers().add(offer);

            buyer.getCart().getAirplaneTickets().remove(airplaneTicketToBuy);

            buyerRepository.save(buyer);
        }
    }

    public void buyFromCartHotels(UUID id, Principal principal) {

        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);

        if (buyer != null) {

            Hotel hotelToBuy = hotelRepository.findHotelById(id);

            Offers offer = offersRepository.findByHotelId(hotelToBuy.getId());
            hotelToBuy.setAvailable(hotelToBuy.getAvailable() - 1);
            hotelToBuy.setSoldNumber(hotelToBuy.getSoldNumber() + 1);
            buyer.getBoughtOffers().add(offer);

            buyer.getCart().getHotels().remove(hotelToBuy);

            buyerRepository.save(buyer);
        }
    }

    public void buyFromCartCarRents(UUID id, Principal principal) {
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);
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

