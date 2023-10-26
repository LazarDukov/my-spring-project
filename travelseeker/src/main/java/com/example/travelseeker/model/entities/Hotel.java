package com.example.travelseeker.model.entities;

import com.example.travelseeker.model.enums.HotelRoomEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotels")
public class Hotel extends BaseEntity {
    @Column
    private String name;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String address;

    @Column
    private int stars;

    @Column
    private String description;

    @Column
    private BigDecimal pricePerNight;

    @Column
    private HotelRoomEnum roomType;

    @Column
    private BigDecimal priceBreakfast;

    @Column
    private BigDecimal priceDinner;

    @Column
    private BigDecimal allInclusive;

    @Column
    private int available;

    @ManyToOne
    private BoughtOffers cart;

    public List<User> getSellers() {
        return sellers;
    }

    public Hotel setSellers(List<User> sellers) {
        this.sellers = sellers;
        return this;
    }

    @ManyToMany
    private List<User> sellers;


    public int getAvailable() {
        return available;
    }

    public Hotel setAvailable(int available) {
        this.available = available;
        return this;
    }


    public Hotel() {
    }

    public Hotel(String name, String country,
                 String city, String address,
                 int stars, String description,
                 BigDecimal pricePerNight,
                 HotelRoomEnum roomType,
                 BigDecimal priceBreakfast,
                 BigDecimal priceDinner,
                 BigDecimal allInclusive,
                 BoughtOffers cart) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.stars = stars;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.roomType = roomType;
        this.priceBreakfast = priceBreakfast;
        this.priceDinner = priceDinner;
        this.allInclusive = allInclusive;
        this.cart = cart;
        this.sellers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Hotel setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Hotel setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Hotel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Hotel setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public Hotel setStars(int stars) {
        this.stars = stars;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Hotel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public Hotel setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
        return this;
    }

    public HotelRoomEnum getRoomType() {
        return roomType;
    }

    public Hotel setRoomType(HotelRoomEnum roomType) {
        this.roomType = roomType;
        return this;
    }

    public BigDecimal getPriceBreakfast() {
        return priceBreakfast;
    }

    public Hotel setPriceBreakfast(BigDecimal priceBreakfast) {
        this.priceBreakfast = priceBreakfast;
        return this;
    }

    public BigDecimal getPriceDinner() {
        return priceDinner;
    }

    public Hotel setPriceDinner(BigDecimal priceDinner) {
        this.priceDinner = priceDinner;
        return this;
    }

    public BigDecimal getAllInclusive() {
        return allInclusive;
    }

    public Hotel setAllInclusive(BigDecimal allInclusive) {
        this.allInclusive = allInclusive;
        return this;
    }

    public BoughtOffers getCart() {
        return cart;
    }

    public Hotel setCart(BoughtOffers cart) {
        this.cart = cart;
        return this;
    }
}
