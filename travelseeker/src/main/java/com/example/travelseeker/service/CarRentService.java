package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.AddCarsDTO;
import com.example.travelseeker.model.entities.CarRent;
import com.example.travelseeker.repository.CarRentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarRentService {

    private final CarRentRepository carRentRepository;

    @Autowired
    public CarRentService(CarRentRepository carRentRepository) {
        this.carRentRepository = carRentRepository;
    }

    public void addNewCar(AddCarsDTO addCarsDTO) {
        CarRent newCarRent = new CarRent().setMake(addCarsDTO.getMake())
                .setModel(addCarsDTO.getModel()).setBodyType(addCarsDTO.getBodyType())
                .setFuelType(addCarsDTO.getFuelType()).setPrice(addCarsDTO.getPrice())
                .setInsurance(addCarsDTO.getInsurance());

        carRentRepository.save(newCarRent);
    }
}
