package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.*;
import com.example.travelseeker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // Find the buyer by username
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);
        if (buyer != null) {
            // Find the airplane ticket to buy
            AirplaneTicket airplaneTicketToBuy = airplaneTicketsRepository.findAirplaneTicketById(id);
            // Find seller of the ticket which will be bought
            Seller seller = airplaneTicketToBuy.getSeller();
            // find Offer by given airplane ticket from tickets by his ID
            Offers offer = offersRepository.findByAirplaneTicketId(airplaneTicketToBuy.getId());

            // airplane ticket available -1
            airplaneTicketToBuy.setAvailable(airplaneTicketToBuy.getAvailable() - 1);
            // sold number of airplane ticket rise up +1
            airplaneTicketToBuy.setSoldNumber(airplaneTicketToBuy.getSoldNumber() + 1);
            if (!sellerRepository.existsById(seller.getId())) {

                // adding offer to sealed offers of seller
                seller.getSealedOffers().add(offer);
                // Seller doesn't exist, save it
                sellerRepository.save(seller);
            }
            // adding offer to bought offers of buyer
            buyer.getBoughtOffers().add(offer);
            //     adding offer to sealed offers of seller


            // remove airplane ticket from cart
            buyer.getCart().getAirplaneTickets().remove(airplaneTicketToBuy);
            // save the seller with new changes
//            sellerRepository.save(seller);
            // saving changes in buyer with new added offers of airplane ticket
            buyerRepository.save(buyer);
        }
    }

    public void buyFromCartHotels(UUID id, Principal principal) {
        // Find the buyer by username
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);

        if (buyer != null) {

            Hotel hotelToBuy = hotelRepository.findHotelById(id);
            //   Seller seller = hotelToBuy.getSeller();
            Offers offer = offersRepository.findByHotelId(hotelToBuy.getId());
            hotelToBuy.setAvailable(hotelToBuy.getAvailable() - 1);
            hotelToBuy.setSoldNumber(hotelToBuy.getSoldNumber() + 1);
            buyer.getBoughtOffers().add(offer);
            //      seller.getSealedOffers().add(offer);
            buyer.getCart().getHotels().remove(hotelToBuy);
            //     sellerRepository.save(seller);
            buyerRepository.save(buyer);
        }
    }

    public void buyFromCartCarRents(UUID id, Principal principal) {
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);
        if (buyer != null) {
            CarRent carRentToBuy = carRentRepository.findCarRentById(id);
            Offers offer = offersRepository.findByCarRentId(carRentToBuy.getId());
            carRentToBuy.setAvailable(carRentToBuy.getAvailable() - 1);
            carRentToBuy.setSoldNumber(carRentToBuy.getSoldNumber() + 1);
            //  Seller seller = carRentToBuy.getSeller();
            buyer.getBoughtOffers().add(offer);
            //      seller.getSealedOffers().add(offer);
            buyer.getCart().getCars().remove(carRentToBuy);
            //   sellerRepository.save(seller);
            buyerRepository.save(buyer);
        }
    }


}

