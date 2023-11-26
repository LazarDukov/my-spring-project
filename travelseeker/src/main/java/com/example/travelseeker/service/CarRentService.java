package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.AddCarsDTO;
import com.example.travelseeker.model.entities.CarRent;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.repository.CarRentRepository;
import com.example.travelseeker.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CarRentService {

    private final CarRentRepository carRentRepository;
    private final SellerRepository sellerRepository;


    @Autowired
    public CarRentService(CarRentRepository carRentRepository, SellerRepository sellerRepository) {
        this.carRentRepository = carRentRepository;

        this.sellerRepository = sellerRepository;
    }

    public CarRent getCarRentById(UUID id) {
        return carRentRepository.findCarRentById(id);

    }


    public void addNewCar(AddCarsDTO addCarsDTO, Principal principal) {
        Seller seller = sellerRepository.findSellerByUsername(principal.getName()).orElse(null);
        CarRent newCarRent = new CarRent().setMake(addCarsDTO.getMake())
                .setModel(addCarsDTO.getModel()).setBodyType(addCarsDTO.getBodyType())
                .setFuelType(addCarsDTO.getFuelType()).setPrice(addCarsDTO.getPrice())
                .setInsurance(addCarsDTO.getInsurance())
                .setSeller(seller)
                .setAvailable(addCarsDTO.getAvailable());
        carRentRepository.save(newCarRent);
    }

    public List<CarRent> getAllCars() {
        return new ArrayList<>(carRentRepository.findAll());
    }

}
