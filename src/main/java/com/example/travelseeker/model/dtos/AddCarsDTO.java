package com.example.travelseeker.model.dtos;

import com.example.travelseeker.model.enums.CarBodyTypeEnum;
import com.example.travelseeker.model.enums.CarFuelTypeEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class AddCarsDTO {

    private String make;


    private String model;


    private CarBodyTypeEnum bodyType;


    private CarFuelTypeEnum fuelType;


    private BigDecimal price;


    private BigDecimal insurance;

    @NotNull
    private int available;

    public int getAvailable() {
        return available;
    }

    public AddCarsDTO setAvailable(int available) {
        this.available = available;
        return this;
    }

    public AddCarsDTO() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarBodyTypeEnum getBodyType() {
        return bodyType;
    }

    public void setBodyType(CarBodyTypeEnum bodyType) {
        this.bodyType = bodyType;
    }

    public CarFuelTypeEnum getFuelType() {
        return fuelType;
    }

    public void setFuelType(CarFuelTypeEnum fuelType) {
        this.fuelType = fuelType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getInsurance() {
        return insurance;
    }

    public void setInsurance(BigDecimal insurance) {
        this.insurance = insurance;
    }
}
