package com.example.travelseeker.model.dtos;

import com.example.travelseeker.model.enums.HotelRoomEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class AddHotelsDTO {
    @NotNull
    private String name;

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private String address;

    @NotNull
    private int stars;

    @NotNull
    private String description;

    @NotNull
    private BigDecimal pricePerNight;

    @NotNull
    private HotelRoomEnum roomType;

    @NotNull
    private BigDecimal priceBreakfast;

    @NotNull
    private BigDecimal priceDinner;

    @NotNull
    private BigDecimal allInclusive;

    public AddHotelsDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public HotelRoomEnum getRoomType() {
        return roomType;
    }

    public void setRoomType(HotelRoomEnum roomType) {
        this.roomType = roomType;
    }

    public BigDecimal getPriceBreakfast() {
        return priceBreakfast;
    }

    public void setPriceBreakfast(BigDecimal priceBreakfast) {
        this.priceBreakfast = priceBreakfast;
    }

    public BigDecimal getPriceDinner() {
        return priceDinner;
    }

    public void setPriceDinner(BigDecimal priceDinner) {
        this.priceDinner = priceDinner;
    }

    public BigDecimal getAllInclusive() {
        return allInclusive;
    }

    public void setAllInclusive(BigDecimal allInclusive) {
        this.allInclusive = allInclusive;
    }
}
