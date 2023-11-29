package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.AddHotelsDTO;
import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.Hotel;
import com.example.travelseeker.model.entities.Offers;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.repository.BuyerRepository;
import com.example.travelseeker.repository.HotelRepository;
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
public class HotelService {
    private final HotelRepository hotelRepository;
    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;
    private final OffersRepository offersRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository, SellerRepository sellerRepository, BuyerRepository buyerRepository, OffersRepository offersRepository) {
        this.hotelRepository = hotelRepository;

        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
        this.offersRepository = offersRepository;
    }

    public void addNewHotel(AddHotelsDTO addHotelsDTO, Principal principal) {
        Seller seller = sellerRepository.findSellerByUsername(principal.getName()).orElse(null);
        Offers hotelOffers = new Offers();
        Hotel newHotel = new Hotel().setName(addHotelsDTO.getName())
                .setCountry(addHotelsDTO.getCountry())
                .setCity(addHotelsDTO.getCity())
                .setAddress(addHotelsDTO.getAddress())
                .setStars(addHotelsDTO.getStars())
                .setDescription(addHotelsDTO.getDescription())
                .setPricePerNight(addHotelsDTO.getPricePerNight())
                .setRoomType(addHotelsDTO.getRoomType())
                .setPriceBreakfast(addHotelsDTO.getPriceBreakfast())
                .setPriceDinner(addHotelsDTO.getPriceDinner())
                .setSeller(seller)
                .setAllInclusive(addHotelsDTO.getAllInclusive());
        assert seller != null;
        hotelOffers.getHotels().add(newHotel);
        hotelOffers.setSeller(seller);
        seller.getPublishedOffers().add(hotelOffers);
        offersRepository.save(hotelOffers);
        sellerRepository.save(seller);
        hotelRepository.save(newHotel);

    }

    public Hotel getHotelById(UUID id) {
        return hotelRepository.findHotelById(id);

    }


    public List<Hotel> getAllHotels() {
        return new ArrayList<>(hotelRepository.findAll());
    }

    public List<Hotel> getBuyerBoughtHotels(Principal principal) {
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);
        assert buyer != null;
        return buyer.getBoughtOffers()
                .stream()
                .flatMap(offer -> offer.getHotels().stream())
                .collect(Collectors.toList());
    }
}
