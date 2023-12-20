package com.example.travelseeker.service;

import com.example.travelseeker.exception.ObjectNotFoundException;
import com.example.travelseeker.model.dtos.AddHotelsDTO;
import com.example.travelseeker.model.entities.Hotel;
import com.example.travelseeker.model.entities.Offers;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.repository.HotelRepository;
import com.example.travelseeker.repository.OffersRepository;
import com.example.travelseeker.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final SellerRepository sellerRepository;
    private final OffersRepository offersRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository, SellerRepository sellerRepository, OffersRepository offersRepository) {
        this.hotelRepository = hotelRepository;

        this.sellerRepository = sellerRepository;

        this.offersRepository = offersRepository;
    }

    public Seller getSellerByUsername(String username) {
        return sellerRepository.findSellerByUsername(username).orElseThrow(() -> new ObjectNotFoundException("Seller with username " + username + " cannot be found!"));

    }

    public void addNewHotel(Principal principal, AddHotelsDTO addHotelsDTO) {
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
                .setSeller(seller)
                .setAvailable(addHotelsDTO.getAvailable()).setSoldNumber(0);
        assert seller != null;
        hotelOffers.getHotels().add(newHotel);
        hotelOffers.setSeller(seller);
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

    public void removePublishedHotel(UUID id) {
        Hotel hotel = hotelRepository.findHotelById(id);
        hotel.setAvailable(0);
        hotelRepository.save(hotel);

    }

    public List<Hotel> getAllAvailableHotelsOfSeller(Principal principal) {
        Seller seller = getSellerByUsername(principal.getName());
        return hotelRepository
                .findHotelsBySellerIdAndAndAvailableGreaterThan(seller.getId(), 0);
    }


    public List<Hotel> hotelsWithQuantityMoreThanZero() {
        return this.hotelRepository.findHotelsByAvailableGreaterThan(0);
    }

    public void removeHotelByAdmin(UUID id) {
        Hotel hotel = hotelRepository.findHotelById(id);
        Offers offer = offersRepository.findByHotelId(id);
        offer.setHotels(null);
        hotel.setSeller(null);
        offersRepository.delete(offer);
        hotelRepository.delete(hotel);

    }

    public Hotel getRandomHotel() {
        List<Hotel> hotels = hotelRepository.findAll();
        Random random = new Random();
        int upperBound = hotels.size();
        int index = random.nextInt(upperBound);
        return hotels.get(index);
    }

    public List<Hotel> getSortedHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream().sorted(Comparator.comparing(Hotel::getPricePerNight)).toList();
    }
}
