package com.example.travelseeker.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @Column
    private BigDecimal totalPrice;

    @Column
    private int count;

    @OneToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @ManyToMany
    private List<Hotel> hotels;
    @ManyToMany
    private List<CarRent> cars;

    @ManyToMany
    private List<AirplaneTicket> airplaneTickets;

    public Cart() {
        this.setBuyer(buyer);
        this.totalPrice = BigDecimal.ZERO;
        this.count = 0;
        this.hotels = new ArrayList<>();
        this.cars = new ArrayList<>();
        this.airplaneTickets = new ArrayList<>();
    }


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Cart setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public int getCount() {
        return count;
    }

    public Cart setCount(int count) {
        this.count = count;
        return this;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Cart setBuyer(Buyer buyer) {
        this.buyer = buyer;
        return this;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public Cart setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
        return this;
    }

    public List<CarRent> getCars() {
        return cars;
    }

    public Cart setCars(List<CarRent> cars) {
        this.cars = cars;
        return this;
    }

    public List<AirplaneTicket> getAirplaneTickets() {
        return airplaneTickets;
    }

    public Cart setAirplaneTickets(List<AirplaneTicket> airplaneTickets) {
        this.airplaneTickets = airplaneTickets;
        return this;
    }
}
