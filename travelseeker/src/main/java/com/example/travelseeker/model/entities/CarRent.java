package com.example.travelseeker.model.entities;

import com.example.travelseeker.model.enums.CarBodyTypeEnum;
import com.example.travelseeker.model.enums.CarFuelTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "car_rents")
public class CarRent extends BaseEntity {
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

    @ManyToOne
    private BoughtOffers cart;

    public CarRent() {
    }

    public CarRent(String make,
                   String model,
                   CarBodyTypeEnum bodyType,
                   CarFuelTypeEnum fuelType,
                   BigDecimal price,
                   BigDecimal insurance,
                   BoughtOffers cart) {
        this.make = make;
        this.model = model;
        this.bodyType = bodyType;
        this.fuelType = fuelType;
        this.price = price;
        this.insurance = insurance;
        this.cart = cart;
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

    public BoughtOffers getCart() {
        return cart;
    }

    public CarRent setCart(BoughtOffers cart) {
        this.cart = cart;
        return this;
    }
}
