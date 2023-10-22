package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.AddHotelsDTO;
import com.example.travelseeker.model.entities.Hotel;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.repository.HotelRepository;
import com.example.travelseeker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository, UserRepository userRepository) {
        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
    }

    public void addNewHotel(AddHotelsDTO addHotelsDTO, Principal principal) {
        Optional<User> user = userRepository.findUserByUsername(principal.getName());
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
                .setAllInclusive(addHotelsDTO.getAllInclusive()).setSeller(user.get());

        hotelRepository.save(newHotel);

    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findHotelById(id);

    }

    public List<Hotel> getAllHotels() {
        List<Hotel> allHotels = new ArrayList<>(hotelRepository.findAll());
        return allHotels;

    }
}
