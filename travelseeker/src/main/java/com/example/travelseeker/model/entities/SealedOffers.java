package com.example.travelseeker.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "sealed_offers")
public class SealedOffers extends BaseEntity {
    @ManyToOne
    private User user;

    @OneToMany
    private List<Hotel> hotels;

    @OneToMany
    private List<CarRent> cars;

    @OneToMany
    private List<AirplaneTicket> airplaneTickets;

    public SealedOffers() {
    }

    public User getUser() {
        return user;
    }

    public SealedOffers setUser(User user) {
        this.user = user;
        return this;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public SealedOffers setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
        return this;
    }

    public List<CarRent> getCars() {
        return cars;
    }

    public SealedOffers setCars(List<CarRent> cars) {
        this.cars = cars;
        return this;
    }

    public List<AirplaneTicket> getAirplaneTickets() {
        return airplaneTickets;
    }

    public SealedOffers setAirplaneTickets(List<AirplaneTicket> airplaneTickets) {
        this.airplaneTickets = airplaneTickets;
        return this;
    }
}
