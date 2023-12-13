package com.example.travelseeker.model.dtos;



import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class AddAirplaneTicketsDTO {
    @NotBlank(message = "Company name cannot be empty!")
    @Size(min = 2, max = 30, message = "Company name should be between 2 and 30 characters!")
    private String companyName;

    @NotEmpty(message = "Please pick a date!")
    private String date;

    @NotBlank(message = "Please select airport for departure!")
    @Size(min = 3, max = 30, message = "Airport name should be between 3 and 30 characters!")
    private String fromAirport;

    @NotBlank(message = "Please select airport for arrive!")
    @Size(min = 3, max = 30, message = "Airport name should be between 3 and 30 characters!")
    private String toAirport;

    @NotBlank(message = "Flight number cannot be empty!")
    @Size(min = 4, max = 6, message = "Flight number should be between 4 and 6 numbers!")
    private String flyNumber;

    @NotNull(message = "Price cannot be empty!")
    @Positive(message = "Price should be more than 0!")
    private BigDecimal price;


    @NotNull
    @Positive(message = "Availability should be more than 0!")
    private Integer available;

    public Integer getAvailable() {
        return available;
    }

    public AddAirplaneTicketsDTO setAvailable(Integer available) {
        this.available = available;
        return this;
    }

    public AddAirplaneTicketsDTO() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public AddAirplaneTicketsDTO setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getDate() {
        return date;
    }

    public AddAirplaneTicketsDTO setDate(String date) {
        this.date = date;
        return this;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public AddAirplaneTicketsDTO setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
        return this;
    }

    public String getToAirport() {
        return toAirport;
    }

    public AddAirplaneTicketsDTO setToAirport(String toAirport) {
        this.toAirport = toAirport;
        return this;
    }

    public String getFlyNumber() {
        return flyNumber;
    }

    public AddAirplaneTicketsDTO setFlyNumber(String flyNumber) {
        this.flyNumber = flyNumber;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddAirplaneTicketsDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

}
