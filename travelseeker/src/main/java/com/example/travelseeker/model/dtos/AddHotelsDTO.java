package com.example.travelseeker.model.dtos;

import com.example.travelseeker.model.enums.HotelRoomEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
    @Min(1)
    @Max(5)
    private int stars;

    @NotNull
    @Size(min = 30)
    private String description;

    @NotNull
    private BigDecimal pricePerNight;

    @NotNull
    private HotelRoomEnum roomType;


    private BigDecimal priceBreakfast;


    private BigDecimal priceDinner;


    private BigDecimal allInclusive;

    @NotNull
    @Min(1)
    private int available;

    public int getAvailable() {
        return available;
    }

    public AddHotelsDTO setAvailable(int available) {
        this.available = available;
        return this;
    }

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
