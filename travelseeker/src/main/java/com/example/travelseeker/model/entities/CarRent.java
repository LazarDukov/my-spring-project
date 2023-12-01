package com.example.travelseeker.model.entities;

import com.example.travelseeker.model.enums.CarBodyTypeEnum;
import com.example.travelseeker.model.enums.CarFuelTypeEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car_rents")
public class CarRent extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    @Column
    private String make;

    @Column
    private String model;

    @Column
    private CarBodyTypeEnum bodyType;

    @Column
    private CarFuelTypeEnum fuelType;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal insurance;

    @Column
    private int available;

    @Column
    private int soldNumber;

    @ManyToMany(mappedBy = "carRents", cascade = CascadeType.PERSIST)
    private List<Offers> offers;


    public CarRent() {
    }

    public int getSoldNumber() {
        return soldNumber;
    }

    public CarRent setSoldNumber(int soldNumber) {
        this.soldNumber = soldNumber;
        return this;
    }

    public Seller getSeller() {
        return seller;
    }

    public CarRent setSeller(Seller seller) {
        this.seller = seller;
        return this;
    }

    public String getMake() {
        return make;
    }

    public CarRent setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarRent setModel(String model) {
        this.model = model;
        return this;
    }

    public CarBodyTypeEnum getBodyType() {
        return bodyType;
    }

    public CarRent setBodyType(CarBodyTypeEnum bodyType) {
        this.bodyType = bodyType;
        return this;
    }

    public CarFuelTypeEnum getFuelType() {
        return fuelType;
    }

    public CarRent setFuelType(CarFuelTypeEnum fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CarRent setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getInsurance() {
        return insurance;
    }

    public CarRent setInsurance(BigDecimal insurance) {
        this.insurance = insurance;
        return this;
    }

    public int getAvailable() {
        return available;
    }

    public CarRent setAvailable(int available) {
        this.available = available;
        return this;
    }

    public List<Offers> getOffers() {
        return offers;
    }

    public CarRent setOffers(List<Offers> offers) {
        this.offers = offers;
        return this;
    }
}
