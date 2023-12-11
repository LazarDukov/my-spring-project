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



    public void AddToCartAirplaneTicket(Principal principal, UUID id) {
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);

        assert buyer != null;
        Cart cartOfBuyer = buyer.getCart();
        AirplaneTicket airplaneTicket = airplaneTicketsRepository.findAirplaneTicketById(id);


        List<AirplaneTicket> airplaneTicketsListOfBuyer = buyer.getCart().getAirplaneTickets();
        airplaneTicketsListOfBuyer.add(airplaneTicket);
        cartOfBuyer.setBuyer(buyer);
        cartOfBuyer.setCount(cartOfBuyer.getCount() + 1);

        cartOfBuyer.setTotalPrice(cartOfBuyer.getTotalPrice().add(airplaneTicket.getPrice()));


        buyerRepository.save(buyer);
        cartRepository.save(cartOfBuyer);
    }

    public void AddToCartHotel(Principal principal, UUID id) {

        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);
        Cart cartOfBuyer = buyer.getCart();
        Hotel hotel = hotelRepository.findHotelById(id);
        List<Hotel> cartHotelsListOfBuyer = buyer.getCart().getHotels();
        cartHotelsListOfBuyer.add(hotel);
        cartOfBuyer.setBuyer(buyer);
        cartOfBuyer.setCount(cartOfBuyer.getCount() + 1);
        cartOfBuyer.setTotalPrice(cartOfBuyer.getTotalPrice().add(hotel.getPricePerNight()));
        buyerRepository.save(buyer);
        cartRepository.save(cartOfBuyer);
    }

    public void AddToCartCar(Principal principal, UUID id) {

        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);
        Cart cartOfBuyer = buyer.getCart();
        CarRent car = carRentRepository.findCarRentById(id);

        List<CarRent> carRentCartListOfBuyer = buyer.getCart().getCars();
        carRentCartListOfBuyer.add(car);
        cartOfBuyer.setBuyer(buyer);
        cartOfBuyer.setCount(cartOfBuyer.getCount() + 1);
        cartOfBuyer.setTotalPrice(cartOfBuyer.getTotalPrice().add(car.getPrice()));


        cartOfBuyer.setTotalPrice(cartOfBuyer.getTotalPrice().add(car.getPrice()));
        buyerRepository.save(buyer);
        cartRepository.save(cartOfBuyer);
    }

    public void removeFromCartAirplaneTicket(Principal principal, UUID id) {
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);

        assert buyer != null;
        Cart cartOfBuyer = buyer.getCart();
        AirplaneTicket airplaneTicket = airplaneTicketsRepository.findAirplaneTicketById(id);
        cartOfBuyer.getAirplaneTickets().remove(airplaneTicket);
        cartOfBuyer.setCount(cartOfBuyer.getCount() - 1);

        cartOfBuyer.setTotalPrice(cartOfBuyer.getTotalPrice().subtract(airplaneTicket.getPrice()));

        if (cartOfBuyer.getTotalPrice().signum() <= 0) {
            cartOfBuyer.setTotalPrice(BigDecimal.ZERO);

        }
        cartRepository.save(cartOfBuyer);
    }

    public void removeFromCartCarRent(Principal principal, UUID id) {
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);

        assert buyer != null;
        Cart cartOfBuyer = buyer.getCart();
        CarRent carRent = carRentRepository.findCarRentById(id);
        cartOfBuyer.getCars().remove(carRent);
        cartOfBuyer.setCount(cartOfBuyer.getCount() - 1);

        cartOfBuyer.setTotalPrice(cartOfBuyer.getTotalPrice().subtract(carRent.getPrice()));

        if (cartOfBuyer.getTotalPrice().signum() <= 0) {
            cartOfBuyer.setTotalPrice(BigDecimal.ZERO);

        }
        cartRepository.save(cartOfBuyer);

    }

    public void removeFromCartHotel(Principal principal, UUID id) {
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);

        assert buyer != null;
        Cart cartOfBuyer = buyer.getCart();
        Hotel hotel = hotelRepository.findHotelById(id);
        cartOfBuyer.getHotels().remove(hotel);
        cartOfBuyer.setCount(cartOfBuyer.getCount() - 1);

        cartOfBuyer.setTotalPrice(cartOfBuyer.getTotalPrice().subtract(hotel.getPricePerNight()));

        if (cartOfBuyer.getTotalPrice().signum() <= 0) {
            cartOfBuyer.setTotalPrice(BigDecimal.ZERO);

        }
        cartRepository.save(cartOfBuyer);

    }
}




