package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.*;
import com.example.travelseeker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    private final AirplaneTicketsRepository airplaneTicketsRepository;

    private final HotelRepository hotelRepository;

    private final CarRentRepository carRentRepository;

    @Autowired

    public CartService(CartRepository cartRepository, UserRepository userRepository, AirplaneTicketsRepository airplaneTicketsRepository, HotelRepository hotelRepository, CarRentRepository carRentRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.airplaneTicketsRepository = airplaneTicketsRepository;
        this.hotelRepository = hotelRepository;
        this.carRentRepository = carRentRepository;
    }

    public Cart getNewCart() {
        Cart newCart = new Cart();
        newCart.setCount(0);
        newCart.setTotalPrice(new BigDecimal(0));
        cartRepository.saveAndFlush(newCart);
        return newCart;
    }

    // TODO: for all these methods should implement counter -- when one of the offers is bought
    public void AddToCartAirplaneTicket(Principal principal, Long id) {
        //TODO: should add functionality about the sum of the price and category shows in cart
        User user = userRepository.findUserByUsername(principal.getName()).orElse(null);

        Cart cart = user.getCart();
        AirplaneTicket airplaneTicket = airplaneTicketsRepository.findAirplaneTicketById(id);

        //TODO: should implement a function where in airplaneTicket entity cart_id to have an Id but for what?
        List<AirplaneTicket> cartList = user.getCart().getAirplaneTickets();
        cartList.add(airplaneTicket);
        airplaneTicket.setAvailable(airplaneTicket.getAvailable() - 1);
        cart.setUser(user);
        cart.setCount(cart.getCount() + 1);
        cart.setTotalPrice(cart.getTotalPrice().add(airplaneTicket.getPrice()).add(airplaneTicket.getMoreLuggagePrice()));
        userRepository.save(user);
        cartRepository.save(cart);
    }

    public void AddToCartHotel(Principal principal, Long id) {
        //TODO: should add functionality about the sum of the price and category shows in cart
        User user = userRepository.findUserByUsername(principal.getName()).orElse(null);
        Cart cart = user.getCart();
        Hotel hotel = hotelRepository.findHotelById(id);

        //TODO: should implement a function where in airplaneTicket entity cart_id to have an Id but for what?
        List<Hotel> cartList = user.getCart().getHotels();
        cartList.add(hotel);
        hotel.setAvailable(hotel.getAvailable() - 1);
        cart.setUser(user);
        cart.setCount(cart.getCount() + 1);
        cart.setTotalPrice(cart.getTotalPrice().add(hotel.getPricePerNight()).add(hotel.getPriceBreakfast()).add(hotel.getPriceDinner()).add(hotel.getAllInclusive()));
        userRepository.save(user);
        cartRepository.save(cart);
    }

    public void AddToCartCar(Principal principal, Long id) {
        //TODO: should add functionality about the sum of the price and category shows in cart
        User user = userRepository.findUserByUsername(principal.getName()).orElse(null);
        Cart cart = user.getCart();
        CarRent car = carRentRepository.findCarRentById(id);

        //TODO: should implement a function where in airplaneTicket entity cart_id to have an Id but for what?

        List<CarRent> cartList = user.getCart().getCars();
        cartList.add(car);
        car.setAvailable(car.getAvailable() - 1);
        cart.setUser(user);
        cart.setCount(cart.getCount() + 1);
        cart.setTotalPrice(cart.getTotalPrice().add(car.getPrice().add(car.getInsurance())));
        userRepository.save(user);
        cartRepository.save(cart);
    }
}
