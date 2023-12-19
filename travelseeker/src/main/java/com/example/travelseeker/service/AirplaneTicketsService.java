package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.AddAirplaneTicketsDTO;
import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.Offers;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AirplaneTicketsService {

    private final AirplaneTicketsRepository airplaneTicketsRepository;
    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;
    private final OffersRepository offersRepository;

    private final CarRentRepository carRentRepository;

    private final HotelRepository hotelRepository;


    @Autowired
    public AirplaneTicketsService(AirplaneTicketsRepository airplaneTicketsRepository,
                                  SellerRepository sellerRepository,
                                  BuyerRepository buyerRepository,
                                  OffersRepository offersRepository,
                                  CarRentRepository carRentRepository,
                                  HotelRepository hotelRepository) {
        this.airplaneTicketsRepository = airplaneTicketsRepository;
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;

        this.offersRepository = offersRepository;
        this.carRentRepository = carRentRepository;
        this.hotelRepository = hotelRepository;
    }

    public AirplaneTicket getAirplaneTicketById(UUID id) {
        return airplaneTicketsRepository.findAirplaneTicketById(id);

    }


    public void addNewAirplaneTicket(Principal principal, @Valid AddAirplaneTicketsDTO addAirplaneTicketsDTO) throws ParseException {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(addAirplaneTicketsDTO.getDateTime(), dateTimeFormatter);


        Seller seller = sellerRepository.findSellerByUsername(principal.getName()).orElse(null);
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
        assert seller != null;
        offersRepository.save(offers);
        sellerRepository.save(seller);
        airplaneTicketsRepository.save(newAirplaneTicket);
    }

    public List<AirplaneTicket> getAllAirplaneTickets() {
        return new ArrayList<>(airplaneTicketsRepository.findAll());
    }

    public void removePublishedAirplaneTicket(Principal principal, UUID id) {
        Seller seller = sellerRepository.findSellerByUsername(principal.getName()).orElse(null);

        if (seller != null) {

            AirplaneTicket airplaneTicket = airplaneTicketsRepository.findAirplaneTicketById(id);
            airplaneTicket.setAvailable(0);
            airplaneTicketsRepository.save(airplaneTicket);
        }
    }


    public AirplaneTicket getSortedAirplaneTickets() {
        List<AirplaneTicket> airplaneTickets = airplaneTicketsRepository.findAll();
        return airplaneTickets.stream().sorted(Comparator.comparing(AirplaneTicket::getPrice)).toList().get(0);
    }

    public List<AirplaneTicket> getAllAvailableAirplaneTicketsOfSeller(Principal principal, int available) {
        Seller seller = sellerRepository.findSellerByUsername(principal.getName()).orElse(null);
        assert seller != null;
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


    public AirplaneTicket getRandomAirplaneTicket() {

        List<AirplaneTicket> airplaneTickets = airplaneTicketsRepository.findAll();
        Random random = new Random();
        int upperBound = airplaneTickets.size();
        int index = random.nextInt(upperBound);
        return airplaneTickets.get(index);
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