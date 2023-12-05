package com.example.travelseeker.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "airplane_tickets")
public class AirplaneTicket extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    @Column
    private String companyName;

    @Column
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column
    private String fromAirport;

    @Column
    private String toAirport;

    @Column
    private String flyNumber;

    @Column
    private BigDecimal price;

    @Column
    private int available;

    @Column
    private int soldNumber;



    @ManyToMany(mappedBy = "airplaneTickets", cascade = CascadeType.PERSIST)
    private List<Offers> offers;

    public AirplaneTicket() {
    }

    public int getAvailable() {
        return available;
    }

    public AirplaneTicket setAvailable(int available) {
        this.available = available;
        return this;
    }

    public Seller getSeller() {
        return seller;
    }

    public AirplaneTicket setSeller(Seller seller) {
        this.seller = seller;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public AirplaneTicket setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public AirplaneTicket setDate(Date date) {
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

    public int getSoldNumber() {
        return soldNumber;
    }

    public AirplaneTicket setSoldNumber(int soldNumber) {
        this.soldNumber = soldNumber;
        return this;
    }

    public List<Offers> getOffers() {
        return offers;
    }

    public AirplaneTicket setOffers(List<Offers> offers) {
        this.offers = offers;
        return this;
    }
}
