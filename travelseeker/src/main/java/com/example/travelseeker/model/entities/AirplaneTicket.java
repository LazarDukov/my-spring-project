package com.example.travelseeker.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "airplane_tickets")
public class AirplaneTicket extends BaseEntity {
    @Column
    private String companyName;

    @Column
    private LocalDateTime date;

    @Column
    private String fromAirport;

    @Column
    private String toAirport;

    @Column
    private String flyNumber;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal moreLuggagePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private BoughtOffers cart;

    public AirplaneTicket() {
    }

    public AirplaneTicket(String companyName,
                          LocalDateTime date,
                          String fromAirport,
                          String toAirport,
                          String flightNumber,
                          BigDecimal price,
                          BigDecimal moreLuggagePrice,
                          BoughtOffers cart) {
        this.companyName = companyName;
        this.date = date;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.flyNumber = flightNumber;
        this.price = price;
        this.moreLuggagePrice = moreLuggagePrice;
        this.cart = cart;
    }

    public String getCompanyName() {
        return companyName;
    }

    public AirplaneTicket setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public AirplaneTicket setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public AirplaneTicket setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
        return this;
    }

    public String getToAirport() {
        return toAirport;
    }

    public AirplaneTicket setToAirport(String toAirport) {
        this.toAirport = toAirport;
        return this;
    }

    public String getFlyNumber() {
        return flyNumber;
    }

    public AirplaneTicket setFlyNumber(String flyNumber) {
        this.flyNumber = flyNumber;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AirplaneTicket setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getMoreLuggagePrice() {
        return moreLuggagePrice;
    }

    public AirplaneTicket setMoreLuggagePrice(BigDecimal moreLuggagePrice) {
        this.moreLuggagePrice = moreLuggagePrice;
        return this;
    }

    public BoughtOffers getCart() {
        return cart;
    }

    public AirplaneTicket setCart(BoughtOffers cart) {
        this.cart = cart;
        return this;
    }
}
