package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.AddCarsDTO;
import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.CarRent;
import com.example.travelseeker.model.entities.Offers;
import com.example.travelseeker.model.entities.Seller;
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


    public void addNewCar(AddCarsDTO addCarsDTO, Principal principal) {
        Seller seller = sellerRepository.findSellerByUsername(principal.getName()).orElse(null);
        Offers carRentOffers = new Offers();
        CarRent newCarRent = new CarRent().setMake(addCarsDTO.getMake())
                .setModel(addCarsDTO.getModel()).setBodyType(addCarsDTO.getBodyType())
                .setFuelType(addCarsDTO.getFuelType()).setPrice(addCarsDTO.getPrice())
                .setInsurance(addCarsDTO.getInsurance())
                .setSeller(seller)
                .setAvailable(addCarsDTO.getAvailable())
                .setSeller(seller).setSoldNumber(0);;
        carRentOffers.getCarRents().add(newCarRent);
        carRentOffers.setSeller(seller);
        assert seller != null;
        seller.getPublishedOffers().add(carRentOffers);
        offersRepository.save(carRentOffers);
        sellerRepository.save(seller);
        carRentRepository.save(newCarRent);


    }

    public List<CarRent> getAllCars() {
        return new ArrayList<>(carRentRepository.findAll());
    }


    public List<CarRent> getBuyerCarRents(Principal principal) {
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);
        assert buyer != null;
        return buyer.getBoughtOffers()
                .stream()
                .flatMap(offer -> offer.getCarRents().stream())
                .collect(Collectors.toList());
    }
}
