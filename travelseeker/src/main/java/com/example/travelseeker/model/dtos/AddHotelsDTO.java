package com.example.travelseeker.model.dtos;

import com.example.travelseeker.model.enums.HotelRoomEnum;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class AddHotelsDTO {
    @NotBlank(message = "Hotel name cannot be empty!")
    @Size(min = 2, max = 30, message = "Hotel name should be between 2 and 30 characters!")
    private String name;

    @NotBlank(message = "Country cannot be empty!")
    @Size(min = 3, max = 30, message = "Country should be between 3 and 30 characters!")
    private String country;

    @NotBlank(message = "City cannot be empty!")
    @Size(min = 3, max = 30, message = "City should be between 3 and 30 characters!")
    private String city;

    @NotBlank(message = "Address cannot be empty!")
    @Size(min = 10, max = 30, message = "City should be between 10 and 50 characters!")
    private String address;

    @NotNull
    @Min(1)
    @Max(5)
    private int stars;

    @NotBlank(message = "Description cannot be empty!")
    @Size(min = 30)
    private String description;

    @NotNull(message = "Price per night cannot be empty and should be more than or equal to 1!")
    @Min(1)
    private BigDecimal pricePerNight;

    @NotNull(message = "Please choose room type!")
    private HotelRoomEnum roomType;



    @NotNull(message = "Available cannot be empty and less than 1!")
    @Min(1)
    private int available;

    public AddHotelsDTO() {
    }

    public String getName() {
        return name;
    }

    public AddHotelsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AddHotelsDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddHotelsDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AddHotelsDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public AddHotelsDTO setStars(int stars) {
        this.stars = stars;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddHotelsDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public AddHotelsDTO setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
        return this;
    }

    public HotelRoomEnum getRoomType() {
        return roomType;
    }

    public AddHotelsDTO setRoomType(HotelRoomEnum roomType) {
        this.roomType = roomType;
        return this;
    }

    public int getAvailable() {
        return available;
    }

    public AddHotelsDTO setAvailable(int available) {
        this.available = available;
        return this;
    }
}
