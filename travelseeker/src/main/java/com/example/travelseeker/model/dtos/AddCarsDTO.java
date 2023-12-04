package com.example.travelseeker.model.dtos;

import com.example.travelseeker.model.enums.CarBodyTypeEnum;
import com.example.travelseeker.model.enums.CarFuelTypeEnum;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class AddCarsDTO {
    @NotBlank(message = "Make cannot be empty!")
    @Size(min = 2, max = 30, message = "Make should be between 2 and 30 characters!")
    private String make;

    @NotBlank(message = "Model cannot be empty!")
    @Size(min = 2, max = 30, message = "Make should be between 2 and 30 characters!")
    private String model;

    @Enumerated
    @NotNull(message = "Please choose a body type!")
    private CarBodyTypeEnum bodyType;

    @Enumerated
    @NotNull(message = "Please choose a fuel type!")
    private CarFuelTypeEnum fuelType;

    @NotNull(message = "Price cannot be empty!")
    @Positive(message = "Price should be more than 0!")
    private BigDecimal price;

    @NotNull(message = "Insurance price cannot be empty!")
    @Positive(message = "If you do not want to add insurance, please write '0'!")
    private BigDecimal insurance;

    @NotNull(message = "Available cannot be empty and less than 1!")
    @Min(1)
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
