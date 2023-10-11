package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.AddAirplaneTicketsDTO;
import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.repository.AirplaneTicketsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AirplaneTicketsService {
    private final AirplaneTicketsRepository airplaneTicketsRepository;

    @Autowired
    public AirplaneTicketsService(AirplaneTicketsRepository airplaneTicketsRepository) {
        this.airplaneTicketsRepository = airplaneTicketsRepository;
    }

    public void addNewAirplaneTicket(@Valid AddAirplaneTicketsDTO addAirplaneTicketsDTO) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(addAirplaneTicketsDTO.getDate());

        AirplaneTicket newAirplaneTicket = new AirplaneTicket()
                .setCompanyName(addAirplaneTicketsDTO.getCompanyName())
                .setDate(date)
                .setFromAirport(addAirplaneTicketsDTO.getFromAirport())
                .setToAirport(addAirplaneTicketsDTO.getToAirport())
                .setFlyNumber(addAirplaneTicketsDTO.getFlyNumber())
                .setPrice(addAirplaneTicketsDTO.getPrice())
                .setMoreLuggagePrice(addAirplaneTicketsDTO.getMoreLuggagePrice());

        airplaneTicketsRepository.save(newAirplaneTicket);
    }

    public List<AirplaneTicket> getAllAirplaneTickets() {
        List<AirplaneTicket> allAirplaneTickets = new ArrayList<>(airplaneTicketsRepository.findAll());
        return allAirplaneTickets;
    }
}
