package com.example.travelseeker.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "bought_offers")
public class BoughtOffers extends BaseEntity {
    @OneToOne
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Hotel> hotels;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<CarRent> cars;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<AirplaneTicket> airplaneTickets;

    public BoughtOffers() {

    }

    public BoughtOffers(User user) {
        this.user = user;
        this.hotels = new ArrayList<>();
        this.cars = new ArrayList<>();
        this.airplaneTickets = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public BoughtOffers setUser(User user) {
        this.user = user;
        return this;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public BoughtOffers setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
        return this;
    }

    public List<CarRent> getCars() {
        return cars;
    }

    public BoughtOffers setCars(List<CarRent> cars) {
        this.cars = cars;
        return this;
    }

    public List<AirplaneTicket> getAirplaneTickets() {
        return airplaneTickets;
    }

    public BoughtOffers setAirplaneTickets(List<AirplaneTicket> airplaneTickets) {
        this.airplaneTickets = airplaneTickets;
        return this;
    }
}
