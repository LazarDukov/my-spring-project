package com.example.travelseeker.web;

import com.example.travelseeker.model.dtos.AddAirplaneTicketsDTO;
import com.example.travelseeker.model.dtos.AddCarsDTO;
import com.example.travelseeker.model.dtos.AddHotelsDTO;
import com.example.travelseeker.repository.AirplaneTicketsRepository;
import com.example.travelseeker.repository.CarRentRepository;
import com.example.travelseeker.repository.HotelRepository;
import com.example.travelseeker.service.AirplaneTicketsService;
import com.example.travelseeker.service.CarRentService;
import com.example.travelseeker.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final AirplaneTicketsRepository airplaneTicketsRepository;
    private final AirplaneTicketsService airplaneTicketsService;

    private final HotelRepository hotelRepository;

    private final HotelService hotelService;

    private final CarRentRepository carRentRepository;

    private final CarRentService carRentService;

    @Autowired
    public OfferController(AirplaneTicketsRepository airplaneTicketsRepository, AirplaneTicketsService airplaneTicketsService, HotelRepository hotelRepository, HotelService hotelService, CarRentRepository carRentRepository, CarRentService carRentService) {
        this.airplaneTicketsRepository = airplaneTicketsRepository;
        this.airplaneTicketsService = airplaneTicketsService;
        this.hotelRepository = hotelRepository;
        this.hotelService = hotelService;
        this.carRentRepository = carRentRepository;
        this.carRentService = carRentService;
    }

    @ModelAttribute("addAirplaneTicketsDTO")
    public AddAirplaneTicketsDTO addAirplaneTicketsDTO() {
        return new AddAirplaneTicketsDTO();
    }

    @ModelAttribute("addHotelsDTO")
    public AddHotelsDTO addHotelsDTO() {
        return new AddHotelsDTO();
    }

    @ModelAttribute("addCarsDTO")
    public AddCarsDTO addCarsDTO() {
        return new AddCarsDTO();
    }

    @GetMapping("/add-offer")
    public String addOffer() {
        return "add-offer";
    }

    @GetMapping("/airplane-tickets")
    public String getAirplaneTicket() {
        return "airplane-tickets";
    }

    @GetMapping("/cars")
    public String getCars() {
        return "cars";
    }

    @GetMapping("/hotels")
    public String getHotels() {
        return "hotels";
    }

    @GetMapping("/add-cars")
    public String getAddCars() {
        return "add-cars";
    }

    @GetMapping("/add-hotels")
    public String getAddHotels() {
        return "add-hotels";
    }

    @GetMapping("/add-airplane-tickets")
    public String getAddAirplaneTickets() {
        return "add-airplane-tickets";
    }


    // TODO: should redirect to right page after successful adding airplane ticket, reformat DATE and adding some restrictions

    @PostMapping("/add-airplane-tickets")
    public String addAirplaneTickets(@Valid AddAirplaneTicketsDTO addAirplaneTicketsDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws ParseException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addAirplaneTicketsDTO", addAirplaneTicketsDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addAirplaneTicketsDTO", bindingResult);
            return "redirect:/offers/home";
        }
        airplaneTicketsService.addNewAirplaneTicket(addAirplaneTicketsDTO);
        return "successfully-added";
    }

    // TODO: should redirect to right page after successful adding hotel and create restrictions in DTO

    @PostMapping("/add-hotels")
    public String addHotels(@Valid AddHotelsDTO addHotelsDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addHotelsDTO", addHotelsDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addHotelsDTO", bindingResult);
            return "redirect:/offers/home";
        }
        hotelService.addNewHotel(addHotelsDTO);
        return "successfully-added";
    }

    // TODO: should redirect to right page after successful adding car and create restrictions in DTO
    @PostMapping("/add-cars")
    public String addCars(@Valid AddCarsDTO addCarsDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addCarsDTO", addCarsDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addCarsDTO", bindingResult);
            return "redirect:/offers/home";
        }
        carRentService.addNewCar(addCarsDTO);
        return "successfully-added";
    }
}
