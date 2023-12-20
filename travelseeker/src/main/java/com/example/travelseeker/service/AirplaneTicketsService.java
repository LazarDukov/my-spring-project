package com.example.travelseeker.service;

import com.example.travelseeker.exception.ObjectNotFoundException;
import com.example.travelseeker.model.dtos.AddAirplaneTicketsDTO;
import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.Offers;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.repository.AirplaneTicketsRepository;
import com.example.travelseeker.repository.OffersRepository;
import com.example.travelseeker.repository.SellerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class AirplaneTicketsService {

    private final AirplaneTicketsRepository airplaneTicketsRepository;
    private final SellerRepository sellerRepository;

    private final OffersRepository offersRepository;


    @Autowired
    public AirplaneTicketsService(AirplaneTicketsRepository airplaneTicketsRepository,
                                  SellerRepository sellerRepository,
                                  OffersRepository offersRepository) {
        this.airplaneTicketsRepository = airplaneTicketsRepository;
        this.sellerRepository = sellerRepository;
        this.offersRepository = offersRepository;

    }

    public AirplaneTicket getAirplaneTicketById(UUID id) {
        return airplaneTicketsRepository.findAirplaneTicketById(id);

    }

    public Seller getSellerByUsername(String username) {
        return sellerRepository.findSellerByUsername(username).orElseThrow(() -> new ObjectNotFoundException("Seller with username " + username + " cannot be found!"));

    }

    public void addNewAirplaneTicket(Principal principal, @Valid AddAirplaneTicketsDTO addAirplaneTicketsDTO) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(addAirplaneTicketsDTO.getDateTime(), dateTimeFormatter);


        Seller seller = getSellerByUsername(principal.getName());
        Offers offers = new Offers();


        AirplaneTicket newAirplaneTicket = new AirplaneTicket()
                .setCompanyName(addAirplaneTicketsDTO.getCompanyName())
                .setDateTime(dateTime)
                .setFromAirport(addAirplaneTicketsDTO.getFromAirport())
                .setToAirport(addAirplaneTicketsDTO.getToAirport())
                .setFlyNumber(addAirplaneTicketsDTO.getFlyNumber())
                .setPrice(addAirplaneTicketsDTO.getPrice())
                .setAvailable(addAirplaneTicketsDTO.getAvailable())
                .setSeller(seller).setSoldNumber(0);

        offers.getAirplaneTickets().add(newAirplaneTicket);
        offers.setSeller(seller);
        offersRepository.save(offers);
        sellerRepository.save(seller);
        airplaneTicketsRepository.save(newAirplaneTicket);
    }

    public List<AirplaneTicket> getAllAirplaneTickets() {
        return new ArrayList<>(airplaneTicketsRepository.findAll());
    }

    public void removePublishedAirplaneTicket(UUID id) {

        AirplaneTicket airplaneTicket = airplaneTicketsRepository.findAirplaneTicketById(id);
        airplaneTicket.setAvailable(0);
        airplaneTicketsRepository.save(airplaneTicket);

    }


    public List<AirplaneTicket> getSortedAirplaneTickets() {
        List<AirplaneTicket> airplaneTickets = airplaneTicketsRepository.findAll();
        return airplaneTickets.stream().sorted(Comparator.comparing(AirplaneTicket::getPrice)).toList();
    }

    public List<AirplaneTicket> getAllAvailableAirplaneTicketsOfSeller(Principal principal) {
        Seller seller = getSellerByUsername(principal.getName());
        return airplaneTicketsRepository
                .findAirplaneTicketsBySellerIdAndAndAvailableGreaterThan(seller.getId(), 0);
    }

    public List<AirplaneTicket> airplaneTicketsWithQuantityMoreThanZero() {
        return this.airplaneTicketsRepository.findAirplaneTicketsByAvailableGreaterThan(0);
    }


    public void removeAirplaneTicketByAdmin(UUID id) {
        AirplaneTicket airplaneTicket = airplaneTicketsRepository.findAirplaneTicketById(id);
        Offers offer = offersRepository.findByAirplaneTicketId(id);
        offer.setAirplaneTickets(null);
        airplaneTicket.setSeller(null);
        offersRepository.delete(offer);
        airplaneTicketsRepository.delete(airplaneTicket);

    }


    public void promotions() {
        List<AirplaneTicket> airplaneTickets = airplaneTicketsRepository.findAll();
        for (AirplaneTicket at : airplaneTickets) {
            BigDecimal originalPrice = at.getPrice();
            at.setPrice(originalPrice.subtract(BigDecimal.valueOf(5)));
            airplaneTicketsRepository.saveAllAndFlush(airplaneTickets);
        }
    }

    public void promotionsEnd() {
        List<AirplaneTicket> airplaneTickets = airplaneTicketsRepository.findAll();
        for (AirplaneTicket at : airplaneTickets) {
            BigDecimal originalPrice = at.getPrice();
            at.setPrice(originalPrice.add(BigDecimal.valueOf(5)));
            airplaneTicketsRepository.saveAllAndFlush(airplaneTickets);
        }

    }

}