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

    private Buyer getBuyer(Principal principal) {
        return buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);
    }

    private Cart buyerCart(Principal principal) {
        Buyer buyer = getBuyer(principal);
        return buyer.getCart();
    }

    private void updateTotalPrice(Cart cart, BigDecimal price, Integer days) {
        cart.setTotalPrice(cart.getTotalPrice().add(price.multiply(BigDecimal.valueOf(days))));
    }


    public void AddToCartAirplaneTicket(Principal principal, UUID id, Integer days) {
        Buyer buyer = getBuyer(principal);
        Cart buyerCart = buyerCart(principal);
        AirplaneTicket airplaneTicket = airplaneTicketsRepository.findAirplaneTicketById(id);
        List<AirplaneTicket> airplaneTickets = buyerCart.getAirplaneTickets();
        for (int i = 0; i < days; i++) {
            airplaneTickets.add(airplaneTicket);

        }
        if (days > 0) {
            buyerCart.setBuyer(buyer);
            buyerCart.setCount(buyerCart.getCount() + 1);
            updateTotalPrice(buyerCart, airplaneTicket.getPrice(), days);
            buyerRepository.save(buyer);
            cartRepository.save(buyerCart);
        }
    }

    public void AddToCartHotel(Principal principal, UUID id, Integer days) {
        Buyer buyer = getBuyer(principal);
        Cart buyerCart = buyerCart(principal);
        Hotel hotel = hotelRepository.findHotelById(id);
        List<Hotel> hotels = buyerCart.getHotels();
        for (int i = 0; i < days; i++) {
            hotels.add(hotel);
        }
        if (days > 0) {
            buyerCart.setBuyer(buyer);
            buyerCart.setCount(buyerCart.getCount() + 1);
            updateTotalPrice(buyerCart, hotel.getPricePerNight(), days);
            buyerRepository.save(buyer);
            cartRepository.save(buyerCart);
        }
    }

    public void AddToCartCar(Principal principal, UUID id, Integer days) {
        Buyer buyer = getBuyer(principal);
        Cart buyerCart = buyerCart(principal);
        CarRent carRent = carRentRepository.findCarRentById(id);
        List<CarRent> carRents = buyerCart.getCars();
        for (int i = 0; i < days; i++) {
            carRents.add(carRent);

        }
        if (days > 0) {
            buyerCart.setBuyer(buyer);
            buyerCart.setCount(buyerCart.getCount() + 1);
            updateTotalPrice(buyerCart, carRent.getPrice(), days);
            buyerRepository.save(buyer);
            cartRepository.save(buyerCart);
        }
    }

    public void removeFromCartAirplaneTicket(Principal principal, UUID id) {
        Buyer buyer = getBuyer(principal);
        Cart cartOfBuyer = buyer.getCart();
        AirplaneTicket airplaneTicket = airplaneTicketsRepository.findAirplaneTicketById(id);
        List<AirplaneTicket> airplaneTicketsListOfBuyer = buyer.getCart().getAirplaneTickets();
        List<AirplaneTicket> airplaneTicketsInCart = airplaneTicketsListOfBuyer.stream().filter(at -> at.getId().equals(airplaneTicket.getId())).toList();
        int numberAirplaneTickets = airplaneTicketsInCart.size();
        BigDecimal priceOfAirplaneTicketsWithSameId = airplaneTicket.getPrice().multiply(BigDecimal.valueOf(numberAirplaneTickets));
        cartOfBuyer.getAirplaneTickets().removeAll(airplaneTicketsInCart);
        cartOfBuyer.setCount(cartOfBuyer.getCount() - 1);
        cartOfBuyer.setTotalPrice(cartOfBuyer.getTotalPrice().subtract(priceOfAirplaneTicketsWithSameId));
        if (cartOfBuyer.getTotalPrice().signum() <= 0) {
            cartOfBuyer.setTotalPrice(BigDecimal.ZERO);

        }
        cartRepository.save(cartOfBuyer);
    }

    public void removeFromCartCarRent(Principal principal, UUID id) {
        Buyer buyer = getBuyer(principal);
        Cart cartOfBuyer = buyer.getCart();
        CarRent car = carRentRepository.findCarRentById(id);
        List<CarRent> carRentCartListOfBuyer = buyer.getCart().getCars();
        List<CarRent> carRentsInThisCart = carRentCartListOfBuyer.stream()
                .filter(carRent -> carRent.getId().equals(car.getId())).toList();
        int numberCarRents = carRentsInThisCart.size();
        BigDecimal priceOfCarRentsWithSameId = car.getPrice().multiply(BigDecimal.valueOf(numberCarRents));
        cartOfBuyer.getCars().removeAll(carRentsInThisCart);
        cartOfBuyer.setCount(cartOfBuyer.getCount() - 1);
        cartOfBuyer.setTotalPrice(cartOfBuyer.getTotalPrice().subtract(priceOfCarRentsWithSameId));
        if (cartOfBuyer.getTotalPrice().signum() <= 0) {
            cartOfBuyer.setTotalPrice(BigDecimal.ZERO);

        }
        cartRepository.save(cartOfBuyer);

    }

    public void removeFromCartHotel(Principal principal, UUID id) {
        Buyer buyer = getBuyer(principal);
        Cart cartOfBuyer = buyer.getCart();
        Hotel hotel = hotelRepository.findHotelById(id);
        List<Hotel> hotelCartListOfBuyer = buyer.getCart().getHotels();
        List<Hotel> hotelInThisCart = hotelCartListOfBuyer.stream().filter(h -> h.getId().equals(hotel.getId())).toList();
        int numberHotelRents = hotelInThisCart.size();
        BigDecimal priceOfHotelWithSameId = hotel.getPricePerNight().multiply(BigDecimal.valueOf(numberHotelRents));
        cartOfBuyer.getHotels().removeAll(hotelInThisCart);
        cartOfBuyer.setCount(cartOfBuyer.getCount() - 1);

        cartOfBuyer.setTotalPrice(cartOfBuyer.getTotalPrice().subtract(priceOfHotelWithSameId));

        if (cartOfBuyer.getTotalPrice().signum() <= 0) {
            cartOfBuyer.setTotalPrice(BigDecimal.ZERO);

        }
        cartRepository.save(cartOfBuyer);

    }
}




