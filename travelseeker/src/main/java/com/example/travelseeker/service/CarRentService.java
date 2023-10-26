package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.AddCarsDTO;
import com.example.travelseeker.model.entities.CarRent;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.repository.CarRentRepository;
import com.example.travelseeker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarRentService {

    private final CarRentRepository carRentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CarRentService(CarRentRepository carRentRepository, UserRepository userRepository) {
        this.carRentRepository = carRentRepository;
        this.userRepository = userRepository;
    }

    public CarRent getCarRentById(UUID id) {
        return carRentRepository.findCarRentById(id);

    }

 //   public CarRent getAllCarRentBySellerId(Long id) {
 //       return carRentRepository.getAllBySellerId(id);
  //  }

    public void addNewCar(AddCarsDTO addCarsDTO, Principal principal) {
        Optional<User> user = userRepository.findUserByUsername(principal.getName());
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
