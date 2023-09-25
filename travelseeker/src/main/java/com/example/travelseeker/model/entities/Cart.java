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

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column
    private int count;

    @ManyToOne
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Hotel> hotels;

    @OneToMany(fetch = FetchType.EAGER)
    private List<CarRent> cars;

    @OneToMany(fetch = FetchType.EAGER)
    private List<AirplaneTicket> airplaneTickets;

    public Cart() {
    }

    public Cart(Category category,
                BigDecimal totalPrice,
                int count,
                User user) {
        this.category = category;
        this.totalPrice = totalPrice;
        this.count = count;
        this.user = user;
        this.hotels = new ArrayList<>();
        this.cars = new ArrayList<>();
        this.airplaneTickets = new ArrayList<>();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }


    public List<CarRent> getCars() {
        return cars;
    }


    public List<AirplaneTicket> getAirplaneTickets() {
        return airplaneTickets;
    }


}
