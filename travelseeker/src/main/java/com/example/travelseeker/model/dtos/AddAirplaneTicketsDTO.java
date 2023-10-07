package com.example.travelseeker.model.dtos;


import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public class AddAirplaneTicketsDTO {
@NotNull
    private String companyName;

    @NotNull
    private String date;

    @NotNull
    private String fromAirport;

    @NotNull
    private String toAirport;

    @NotNull
    private String flyNumber;

    @NotNull
    private BigDecimal price;

    @NotNull
    private BigDecimal moreLuggagePrice;

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

    public BigDecimal getMoreLuggagePrice() {
        return moreLuggagePrice;
    }

    public AddAirplaneTicketsDTO setMoreLuggagePrice(BigDecimal moreLuggagePrice) {
        this.moreLuggagePrice = moreLuggagePrice;
        return this;
    }
}
