package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.AddCarsDTO;
import com.example.travelseeker.model.entities.*;
import com.example.travelseeker.repository.BuyerRepository;
import com.example.travelseeker.repository.CarRentRepository;
import com.example.travelseeker.repository.OffersRepository;
import com.example.travelseeker.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarRentService {

    private final CarRentRepository carRentRepository;
    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;
    private final OffersRepository offersRepository;


    @Autowired
    public CarRentService(CarRentRepository carRentRepository, SellerRepository sellerRepository, BuyerRepository buyerRepository, OffersRepository offersRepository) {
        this.carRentRepository = carRentRepository;

        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
        this.offersRepository = offersRepository;
    }

    public CarRent getCarRentById(UUID id) {
        return carRentRepository.findCarRentById(id);

    }


    public void addNewCar(Principal principal,AddCarsDTO addCarsDTO) {
        Seller seller = sellerRepository.findSellerByUsername(principal.getName()).orElse(null);
        Offers carRentOffers = new Offers();
        CarRent newCarRent = new CarRent().setMake(addCarsDTO.getMake())
                .setModel(addCarsDTO.getModel()).setBodyType(addCarsDTO.getBodyType())
                .setFuelType(addCarsDTO.getFuelType()).setPrice(addCarsDTO.getPrice())
                .setSeller(seller)
                .setAvailable(addCarsDTO.getAvailable())
                .setSeller(seller).setSoldNumber(0);
        ;
        carRentOffers.getCarRents().add(newCarRent);
        carRentOffers.setSeller(seller);
        assert seller != null;
        // seller.getPublishedOffers().add(carRentOffers);
        offersRepository.save(carRentOffers);
        sellerRepository.save(seller);
        carRentRepository.save(newCarRent);


    }

    public List<CarRent> getAllCars() {
        return new ArrayList<>(carRentRepository.findAll());
    }


    public void removePublishedCarRent(Principal principal, UUID id) {
        Seller seller = sellerRepository.findSellerByUsername(principal.getName()).orElse(null);

        if (seller != null) {

            CarRent carRent = carRentRepository.findCarRentById(id);
            carRent.setAvailable(0);
            carRentRepository.save(carRent);
            // Handle the case where the seller or ticket is not found
        }
    }

    public List<CarRent> getAllAvailableCarsRentOfSeller(Principal principal, int available) {
        Seller seller = sellerRepository.findSellerByUsername(principal.getName()).orElse(null);
        assert seller != null;
        return carRentRepository
                .findCarRentsBySellerIdAndAndAvailableGreaterThan(seller.getId(), 0);
    }

    public List<CarRent> carsWithQuantityMoreThanZero() {
        return this.carRentRepository.findCarRentsByAvailableGreaterThan(0);
    }
}
