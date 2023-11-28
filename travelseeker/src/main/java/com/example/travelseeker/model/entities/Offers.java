package com.example.travelseeker.model.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offers")
public class Offers extends BaseEntity {

    @ManyToOne
    private Seller sellerId;

    @ManyToMany(mappedBy = "boughtOffers", cascade = CascadeType.PERSIST)
    private List<Buyer> buyers;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<AirplaneTicket> airplaneTickets;
    @ManyToMany
    private List<Hotel> hotels;
    @ManyToMany
    private List<CarRent> carRents;

    public Offers() {
        this.buyers = new ArrayList<>();
        this.airplaneTickets = new ArrayList<>();
        this.hotels = new ArrayList<>();
        this.carRents = new ArrayList<>();
    }

    public Seller getSellerId() {
        return sellerId;
    }

    public Offers setSellerId(Seller sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public List<Buyer> getBuyers() {
        return buyers;
    }

    public Offers setBuyers(List<Buyer> buyers) {
        this.buyers = buyers;
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
