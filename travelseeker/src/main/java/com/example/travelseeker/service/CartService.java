package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.Cart;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.repository.AirplaneTicketsRepository;
import com.example.travelseeker.repository.CartRepository;
import com.example.travelseeker.repository.UserRepository;
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

    @Autowired

    public CartService(CartRepository cartRepository, UserRepository userRepository, AirplaneTicketsRepository airplaneTicketsRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.airplaneTicketsRepository = airplaneTicketsRepository;
    }

    public Cart getNewCart() {
        Cart newCart = new Cart();
        newCart.setCount(0);
        newCart.setTotalPrice(new BigDecimal(0));
        cartRepository.saveAndFlush(newCart);
        return newCart;
    }

    public void AddToCartAirplaneTicket(Principal principal, Long id) {
        //TODO: should add functionality about the sum of the price and category shows in cart
        User user = userRepository.findUserByUsername(principal.getName()).orElse(null);
        Long cartId = user.getCart().getId();
        Cart cart = user.getCart();
        AirplaneTicket airplaneTicket = airplaneTicketsRepository.findAirplaneTicketById(id);

        //TODO: should implement a function where in airplaneTicket entity cart_id to have an Id but for what?
        List<AirplaneTicket> cartList = user.getCart().getAirplaneTickets();
        cartList.add(airplaneTicket);
        cart.setUser(user);
        cart.setCount(cart.getCount() + 1);
        cart.setTotalPrice(cart.getTotalPrice().add(airplaneTicket.getPrice()).add(airplaneTicket.getMoreLuggagePrice()));
        userRepository.save(user);
        cartRepository.save(cart);
    }
}
