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
public class CartService {
    private final CartRepository cartRepository;

    private final AirplaneTicketsRepository airplaneTicketsRepository;

    private final HotelRepository hotelRepository;

    private final CarRentRepository carRentRepository;

    private final BuyerRepository buyerRepository;

    @Autowired

    public CartService(CartRepository cartRepository, AirplaneTicketsRepository airplaneTicketsRepository,
                       HotelRepository hotelRepository, CarRentRepository carRentRepository, BuyerRepository buyerRepository) {
        this.cartRepository = cartRepository;
        this.airplaneTicketsRepository = airplaneTicketsRepository;
        this.hotelRepository = hotelRepository;
        this.carRentRepository = carRentRepository;
        this.buyerRepository = buyerRepository;
    }

    public Cart getNewCart() {
        Cart newCart = new Cart();
        newCart.setCount(0);
        newCart.setTotalPrice(new BigDecimal(0));
        cartRepository.saveAndFlush(newCart);
        return newCart;
    }

    //TODO:for all these methods should implement counter --when one of the offers is bought

    public void AddToCartAirplaneTicket(Principal principal, UUID id) {
        //TODO: should add functionality about the sum of the price and category shows in cart
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);

        assert buyer != null;
        Cart cartOfBuyer = buyer.getCart();
        AirplaneTicket airplaneTicket = airplaneTicketsRepository.findAirplaneTicketById(id);

        //TODO: should implement a function where in airplaneTicket entity cart_id to have an Id but for what?
        List<AirplaneTicket> airplaneTicketsListOfBuyer = buyer.getCart().getAirplaneTickets();
        airplaneTicketsListOfBuyer.add(airplaneTicket);

        cartOfBuyer.setBuyer(buyer);
        cartOfBuyer.setCount(cartOfBuyer.getCount() + 1);
        cartOfBuyer.setTotalPrice(cartOfBuyer.getTotalPrice().add(airplaneTicket.getPrice()).add(airplaneTicket.getMoreLuggagePrice()));
        buyerRepository.save(buyer);
        cartRepository.save(cartOfBuyer);
    }

    public void AddToCartHotel(Principal principal, UUID id) {
        //TODO: should add functionality about the sum of the price and category shows in cart
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);
        Cart cartOfBuyer = buyer.getCart();
        Hotel hotel = hotelRepository.findHotelById(id);
        //TODO: should implement a function where in airplaneTicket entity cart_id to have an Id but for what?
        List<Hotel> cartHotelsListOfBuyer = buyer.getCart().getHotels();
        cartHotelsListOfBuyer.add(hotel);
        cartOfBuyer.setBuyer(buyer);
        cartOfBuyer.setCount(cartOfBuyer.getCount() + 1);
        cartOfBuyer.setTotalPrice(cartOfBuyer.getTotalPrice().add(hotel.getPricePerNight()).add(hotel.getPriceBreakfast()).add(hotel.getPriceDinner()).add(hotel.getAllInclusive()));
        buyerRepository.save(buyer);
        cartRepository.save(cartOfBuyer);
    }

    public void AddToCartCar(Principal principal, UUID id) {
        //TODO: should add functionality about the sum of the price and category shows in cart
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);
        Cart cartOfBuyer = buyer.getCart();
        CarRent car = carRentRepository.findCarRentById(id);

        //TODO: should implement a function where in airplaneTicket entity cart_id to have an Id but for what?

        List<CarRent> carRentCartListOfBuyer = buyer.getCart().getCars();
        carRentCartListOfBuyer.add(car);
        cartOfBuyer.setBuyer(buyer);
        cartOfBuyer.setCount(cartOfBuyer.getCount() + 1);
        cartOfBuyer.setTotalPrice(cartOfBuyer.getTotalPrice().add(car.getPrice().add(car.getInsurance())));
        buyerRepository.save(buyer);
        cartRepository.save(cartOfBuyer);
    }
}




