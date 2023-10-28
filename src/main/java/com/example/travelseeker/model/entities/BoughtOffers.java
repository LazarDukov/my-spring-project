package com.example.travelseeker.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;


@Entity
@Table(name = "bought_offers")
public class BoughtOffers extends BaseEntity {
    @ManyToOne
    private User user;

    @OneToMany
    private List<Hotel> hotels;

    @OneToMany
    private List<CarRent> cars;

    @OneToMany
    private List<AirplaneTicket> airplaneTickets;

    public BoughtOffers() {
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
