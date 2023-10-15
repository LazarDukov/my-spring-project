package com.example.travelseeker.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {
    @OneToOne
    private Category category;

    @Column
    private BigDecimal totalPrice;

    @Column
    private int count;

    @OneToOne
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Hotel> hotels;

    @OneToMany(fetch = FetchType.EAGER)
    private List<CarRent> cars;

    @OneToMany(fetch = FetchType.EAGER)
    private List<AirplaneTicket> airplaneTickets;

    public Cart() {
    }

    public Cart(Category category, BigDecimal totalPrice, int count, User user) {
        this.category = category;
        this.totalPrice = totalPrice;
        this.count = count;
        this.user = user;
        this.airplaneTickets = new ArrayList<>();
        this.hotels = new ArrayList<>();
        this.cars = new ArrayList<>();
    }

    public Category getCategory() {
        return category;
    }

    public Cart setCategory(Category category) {
        this.category = category;
        return this;
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

    public User getUser() {
        return user;
    }

    public Cart setUser(User user) {
        this.user = user;
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
