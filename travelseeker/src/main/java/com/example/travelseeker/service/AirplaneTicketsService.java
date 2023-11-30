package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.AddAirplaneTicketsDTO;
import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.Offers;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.repository.AirplaneTicketsRepository;
import com.example.travelseeker.repository.BuyerRepository;
import com.example.travelseeker.repository.OffersRepository;
import com.example.travelseeker.repository.SellerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AirplaneTicketsService {
    private final AirplaneTicketsRepository airplaneTicketsRepository;
    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;
    private final OffersRepository offersRepository;


    @Autowired
    public AirplaneTicketsService(AirplaneTicketsRepository airplaneTicketsRepository, SellerRepository sellerRepository, BuyerRepository buyerRepository, OffersRepository offersRepository) {
        this.airplaneTicketsRepository = airplaneTicketsRepository;
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;

        this.offersRepository = offersRepository;
    }

    public AirplaneTicket getAirplaneTicketById(UUID id) {
        return airplaneTicketsRepository.findAirplaneTicketById(id);

    }


    public void addNewAirplaneTicket(@Valid AddAirplaneTicketsDTO addAirplaneTicketsDTO, Principal principal) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = dateFormat.parse(addAirplaneTicketsDTO.getDate());

        Seller seller = sellerRepository.findSellerByUsername(principal.getName()).orElse(null);
        Offers offers = new Offers();


        AirplaneTicket newAirplaneTicket = new AirplaneTicket()
                .setCompanyName(addAirplaneTicketsDTO.getCompanyName())
                .setDate(date)
                .setFromAirport(addAirplaneTicketsDTO.getFromAirport())
                .setToAirport(addAirplaneTicketsDTO.getToAirport())
                .setFlyNumber(addAirplaneTicketsDTO.getFlyNumber())
                .setPrice(addAirplaneTicketsDTO.getPrice())
                .setMoreLuggagePrice(addAirplaneTicketsDTO.getMoreLuggagePrice())
                .setAvailable(addAirplaneTicketsDTO.getAvailable())
                .setSeller(seller).setSoldNumber(0);

        offers.getAirplaneTickets().add(newAirplaneTicket);
        offers.setSeller(seller);
        assert seller != null;
        seller.getPublishedOffers().add(offers);
        offersRepository.save(offers);
        sellerRepository.save(seller);
        airplaneTicketsRepository.save(newAirplaneTicket);
    }

    public List<AirplaneTicket> getAllAirplaneTickets() {
        return new ArrayList<>(airplaneTicketsRepository.findAll());
    }

    public List<AirplaneTicket> getBuyerBoughtAirplaneTickets(Principal principal) {
        Buyer buyer = buyerRepository.findBuyerByUsername(principal.getName()).orElse(null);
        assert buyer != null;
        return buyer.getBoughtOffers()
                .stream()
                .flatMap(offers -> offers.getAirplaneTickets().stream())
                .collect(Collectors.toList());
    }

}
