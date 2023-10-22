package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.AddCarsDTO;
import com.example.travelseeker.model.entities.CarRent;
import com.example.travelseeker.repository.CarRentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarRentService {

    private final CarRentRepository carRentRepository;

    @Autowired
    public CarRentService(CarRentRepository carRentRepository) {
        this.carRentRepository = carRentRepository;
    }

    public CarRent getCarRentById(Long id) {
        return carRentRepository.findCarRentById(id);

    }

    public void addNewCar(AddCarsDTO addCarsDTO) {
        CarRent newCarRent = new CarRent().setMake(addCarsDTO.getMake())
                .setModel(addCarsDTO.getModel()).setBodyType(addCarsDTO.getBodyType())
                .setFuelType(addCarsDTO.getFuelType()).setPrice(addCarsDTO.getPrice())
                .setInsurance(addCarsDTO.getInsurance())
                .setAvailable(addCarsDTO.getAvailable());

        carRentRepository.save(newCarRent);
    }

    public List<CarRent> getAllCars() {
        List<CarRent> allCars = new ArrayList<>(carRentRepository.findAll());
        return allCars;
    }
}
