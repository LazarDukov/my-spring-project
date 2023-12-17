package com.example.travelseeker.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private LocalDateTime dateTime;

//    @Column
//    @Temporal(TemporalType.TIME)
//    private LocalTime time;

//    public LocalTime getTime() {
//        return time;
//    }
//
//    public AirplaneTicket setTime(LocalTime time) {
//        this.time = time;
//        return this;
//    }

    @Column
    private String fromAirport;

    @Column
    private String toAirport;

    @Column
    private String flyNumber;

    @Column
    private BigDecimal price;

    @Column
    private Integer available;

    @Column
    private Integer soldNumber;


    @ManyToMany(mappedBy = "airplaneTickets", cascade = CascadeType.PERSIST)
    private List<Offers> offers;

    public AirplaneTicket() {
    }

    public Integer getAvailable() {
        return available;
    }

    public AirplaneTicket setAvailable(Integer available) {
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

//    public LocalDate getDate() {
//        return date;
//    }
//
//    public AirplaneTicket setDate(LocalDate date) {
//        this.date = date;
//        return this;
//    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public AirplaneTicket setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

    public Integer getSoldNumber() {
        return soldNumber;
    }

    public AirplaneTicket setSoldNumber(Integer soldNumber) {
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
