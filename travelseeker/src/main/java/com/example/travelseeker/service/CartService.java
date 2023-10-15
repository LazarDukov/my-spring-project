package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.Cart;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.repository.AirplaneTicketsRepository;
import com.example.travelseeker.repository.CartRepository;
import com.example.travelseeker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    private final AirplaneTicketsRepository airplaneTicketsRepository;

    @Autowired

    public CartService(CartRepository cartRepository, UserRepository userRepository, AirplaneTicketsRepository airplaneTicketsRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.airplaneTicketsRepository = airplaneTicketsRepository;
    }

    public Cart getNewCart() {
        Cart newCart = new Cart();
        cartRepository.saveAndFlush(newCart);
        return newCart;
    }

    public void AddToCartAirplaneTicket(Principal principal, Long id) {
        //TODO: should add functionality about the sum of the price and category shows in cart
        User user = userRepository.findUserByUsername(principal.getName()).orElse(null);
        AirplaneTicket airplaneTicket = airplaneTicketsRepository.findAirplaneTicketById(id);
        List<AirplaneTicket> cart = user.getCart().getAirplaneTickets();
        cart.add(airplaneTicket);
        userRepository.save(user);
    }
}
