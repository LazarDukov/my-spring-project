package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.AddHotelsDTO;
import com.example.travelseeker.model.entities.Hotel;
import com.example.travelseeker.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void addNewHotel(AddHotelsDTO addHotelsDTO) {
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
                .setAllInclusive(addHotelsDTO.getAllInclusive());

        hotelRepository.save(newHotel);

    }
}
