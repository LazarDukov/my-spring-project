package com.example.travelseeker.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "bought_offers")
public class BoughtOffers extends BaseEntity {
    @ManyToOne
    private User user;

    @OneToMany
    private List<Hotel> hotelsBought;

    @OneToMany
    private List<CarRent> carsRented;

    @OneToMany
    private List<AirplaneTicket> airplaneTicketsBought;

    public BoughtOffers() {
    }

    public BoughtOffers(User user) {
        this.user = user;
        this.hotelsBought = new ArrayList<>();
        this.carsRented = new ArrayList<>();
        this.airplaneTicketsBought = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Hotel> getHotelsBought() {
        return hotelsBought;
    }



    public List<CarRent> getCarsRented() {
        return carsRented;
    }



    public List<AirplaneTicket> getAirplaneTicketsBought() {
        return airplaneTicketsBought;
    }


}
