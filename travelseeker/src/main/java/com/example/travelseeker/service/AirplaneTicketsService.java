package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.AddAirplaneTicketsDTO;
import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.repository.AirplaneTicketsRepository;
import com.example.travelseeker.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AirplaneTicketsService {
    private final AirplaneTicketsRepository airplaneTicketsRepository;
    private final UserRepository userRepository;

    @Autowired
    public AirplaneTicketsService(AirplaneTicketsRepository airplaneTicketsRepository, UserRepository userRepository) {
        this.airplaneTicketsRepository = airplaneTicketsRepository;
        this.userRepository = userRepository;
    }

    public AirplaneTicket getAirplaneTicketById(Long id) {
        return airplaneTicketsRepository.findAirplaneTicketById(id);

    }

    public AirplaneTicket getAllBySellerId(Long id) {
        return airplaneTicketsRepository.getAllBySellerId(id);
    }

    public void addNewAirplaneTicket(@Valid AddAirplaneTicketsDTO addAirplaneTicketsDTO, Principal principal) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(addAirplaneTicketsDTO.getDate());
        Optional<User> user = userRepository.findUserByUsername(principal.getName());
        AirplaneTicket newAirplaneTicket = new AirplaneTicket()
                .setCompanyName(addAirplaneTicketsDTO.getCompanyName())
                .setDate(date)
                .setFromAirport(addAirplaneTicketsDTO.getFromAirport())
                .setToAirport(addAirplaneTicketsDTO.getToAirport())
                .setFlyNumber(addAirplaneTicketsDTO.getFlyNumber())
                .setPrice(addAirplaneTicketsDTO.getPrice())
                .setMoreLuggagePrice(addAirplaneTicketsDTO.getMoreLuggagePrice())
                .setAvailable(addAirplaneTicketsDTO.getAvailable())
                .setSeller(user.get());

        airplaneTicketsRepository.save(newAirplaneTicket);
    }

    public List<AirplaneTicket> getAllAirplaneTickets() {
        List<AirplaneTicket> allAirplaneTickets = new ArrayList<>(airplaneTicketsRepository.findAll());
        return allAirplaneTickets;
    }
}
