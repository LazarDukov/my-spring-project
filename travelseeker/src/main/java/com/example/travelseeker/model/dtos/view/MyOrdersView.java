package com.example.travelseeker.model.dtos.view;

import com.example.travelseeker.model.enums.CarBodyTypeEnum;
import com.example.travelseeker.model.enums.CarFuelTypeEnum;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class MyOrdersView {

    private String companyName;
    private String fromAirport;
    private String toAirport;
    private String flyNumber;

    private String make;
    private String model;
    private CarFuelTypeEnum fuelType;

    private String name;
    private String country;
    private String city;

    public MyOrdersView() {
    }

    public MyOrdersView(String companyName, String fromAirport, String toAirport, String flyNumber, String make, String model, CarFuelTypeEnum fuelType, String name, String country, String city) {
        this.companyName = companyName;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.flyNumber = flyNumber;
        this.make = make;
        this.model = model;
        this.fuelType = fuelType;
        this.name = name;
        this.country = country;
        this.city = city;
    }

    public String getCompanyName() {
        return companyName;
    }

    public MyOrdersView setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public MyOrdersView setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
        return this;
    }

    public String getToAirport() {
        return toAirport;
    }

    public MyOrdersView setToAirport(String toAirport) {
        this.toAirport = toAirport;
        return this;
    }

    public String getFlyNumber() {
        return flyNumber;
    }

    public MyOrdersView setFlyNumber(String flyNumber) {
        this.flyNumber = flyNumber;
        return this;
    }

    public String getMake() {
        return make;
    }

    public MyOrdersView setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public MyOrdersView setModel(String model) {
        this.model = model;
        return this;
    }

    public CarFuelTypeEnum getFuelType() {
        return fuelType;
    }

    public MyOrdersView setFuelType(CarFuelTypeEnum fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public String getName() {
        return name;
    }

    public MyOrdersView setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public MyOrdersView setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public MyOrdersView setCity(String city) {
        this.city = city;
        return this;
    }
}
