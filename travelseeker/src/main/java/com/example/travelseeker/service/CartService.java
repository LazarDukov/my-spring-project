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
        buyerCart.setBuyer(buyer);
        buyerCart.setCount(buyerCart.getCount() + 1);
        updateTotalPrice(buyerCart, airplaneTicket.getPrice(), days);
        buyerRepository.save(buyer);
        cartRepository.save(buyerCart);
    }

    public void AddToCartHotel(Principal principal, UUID id, Integer days) {

        Buyer buyer = getBuyer(principal);
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

    public void AddToCartCar(Principal principal, UUID id, Integer days) {
        Buyer buyer = getBuyer(principal);
        Cart buyerCart = buyerCart(principal);
        CarRent carRent = carRentRepository.findCarRentById(id);
        List<CarRent> carRents = buyerCart.getCars();
        //TODO: IF BUYER add to cart offer which is already added throw a message for its already added
        for (int i = 0; i < days; i++) {
            carRents.add(carRent);

        }
        buyerCart.setBuyer(buyer);
        buyerCart.setCount(buyerCart.getCount() + 1);
        updateTotalPrice(buyerCart, carRent.getPrice(), days);
        buyerRepository.save(buyer);
        cartRepository.save(buyerCart);
    }

    public void removeFromCartAirplaneTicket(Principal principal, UUID id) {
        Buyer buyer = getBuyer(principal);
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
        Buyer buyer = getBuyer(principal);
        assert buyer != null;
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




