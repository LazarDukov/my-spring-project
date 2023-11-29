package com.example.travelseeker.model.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offers")
public class Offers extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;


    @ManyToMany(mappedBy = "boughtOffers", cascade = CascadeType.PERSIST)
    private List<Buyer> buyers;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "offer_airplane_ticket",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    private List<AirplaneTicket> airplaneTickets;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "offer_hotel",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_id"))
    private List<Hotel> hotels;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "offer_car_rent",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "car_rent_id")
    )
    private List<CarRent> carRents;

    public Offers() {
        this.buyers = new ArrayList<>();
        this.airplaneTickets = new ArrayList<>();
        this.hotels = new ArrayList<>();
        this.carRents = new ArrayList<>();
    }


    public List<Buyer> getBuyers() {
        return buyers;
    }

    public Offers setBuyers(List<Buyer> buyers) {
        this.buyers = buyers;
        return this;
    }

    public Seller getSeller() {
        return seller;
    }

    public Offers setSeller(Seller seller) {
        this.seller = seller;
        return this;
    }

    public List<AirplaneTicket> getAirplaneTickets() {
        return airplaneTickets;
    }

    public Offers setAirplaneTickets(List<AirplaneTicket> airplaneTickets) {
        this.airplaneTickets = airplaneTickets;
        return this;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public Offers setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
        return this;
    }

    public List<CarRent> getCarRents() {
        return carRents;
    }

    public Offers setCarRents(List<CarRent> carRents) {
        this.carRents = carRents;
        return this;
    }
}
