package com.example.travelseeker.service;

import com.example.travelseeker.exception.ObjectNotFoundException;
import com.example.travelseeker.model.dtos.AddCarsDTO;
import com.example.travelseeker.model.entities.CarRent;
import com.example.travelseeker.model.entities.Offers;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.repository.CarRentRepository;
import com.example.travelseeker.repository.OffersRepository;
import com.example.travelseeker.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
public class CarRentService {

    private final CarRentRepository carRentRepository;
    private final SellerRepository sellerRepository;
    private final OffersRepository offersRepository;


    @Autowired
    public CarRentService(CarRentRepository carRentRepository, SellerRepository sellerRepository, OffersRepository offersRepository) {
        this.carRentRepository = carRentRepository;

        this.sellerRepository = sellerRepository;

        this.offersRepository = offersRepository;
    }

    public CarRent getCarRentById(UUID id) {
        return carRentRepository.findCarRentById(id);

    }

    public Seller getSellerByUsername(String username) {
        return sellerRepository.findSellerByUsername(username).orElseThrow(() -> new ObjectNotFoundException("Seller with username " + username + " cannot be found!"));

    }

    public void addNewCar(Principal principal, AddCarsDTO addCarsDTO) {
        Seller seller = getSellerByUsername(principal.getName());
        Offers carRentOffers = new Offers();
        CarRent newCarRent = new CarRent().setMake(addCarsDTO.getMake())
                .setModel(addCarsDTO.getModel()).setBodyType(addCarsDTO.getBodyType())
                .setFuelType(addCarsDTO.getFuelType()).setPrice(addCarsDTO.getPrice())
                .setSeller(seller)
                .setAvailable(addCarsDTO.getAvailable())
                .setSeller(seller).setSoldNumber(0);
        carRentOffers.getCarRents().add(newCarRent);
        carRentOffers.setSeller(seller);
        offersRepository.save(carRentOffers);
        sellerRepository.save(seller);
        carRentRepository.save(newCarRent);


    }

    public List<CarRent> getAllCars() {
        return new ArrayList<>(carRentRepository.findAll());
    }

    public List<CarRent> getSortedCarRents() {
        List<CarRent> carRents = carRentRepository.findAll();
        return carRents.stream().sorted(Comparator.comparing(CarRent::getPrice)).toList();
    }

    public void removePublishedCarRent(Principal principal, UUID id) {
        CarRent carRent = carRentRepository.findCarRentById(id);
        carRent.setAvailable(0);
        carRentRepository.save(carRent);

    }

    public List<CarRent> getAllAvailableCarsRentOfSeller(Principal principal) {
        return carRentRepository
                .findCarRentsBySellerIdAndAndAvailableGreaterThan(getSellerByUsername(principal.getName()).getId(), 0);
    }

    public List<CarRent> carsWithQuantityMoreThanZero() {
        return this.carRentRepository.findCarRentsByAvailableGreaterThan(0);
    }

    public void removeCarRentByAdmin(UUID id) {
        CarRent carRent = carRentRepository.findCarRentById(id);
        Offers offer = offersRepository.findByCarRentId(id);
        offer.setCarRents(null);
        carRent.setSeller(null);
        offersRepository.delete(offer);
        carRentRepository.delete(carRent);

    }

    public CarRent getRandomCarRent() {
        List<CarRent> carRents = carRentRepository.findAll();
        Random random = new Random();
        int upperBound = carRents.size();
        int index = random.nextInt(upperBound);
        return carRents.get(index);
    }


}
