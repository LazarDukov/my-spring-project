package com.example.travelseeker.web;

import com.example.travelseeker.model.dtos.AddAirplaneTicketsDTO;
import com.example.travelseeker.model.dtos.AddCarsDTO;
import com.example.travelseeker.model.dtos.AddHotelsDTO;
import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.text.ParseException;

@Controller
@RequestMapping("/offers")
public class OfferController {


    private final AirplaneTicketsService airplaneTicketsService;


    private final HotelService hotelService;


    private final CarRentService carRentService;


    private final CartService cartService;

    private final UserService userService;

    @Autowired
    public OfferController(AirplaneTicketsService airplaneTicketsService, HotelService hotelService, CarRentService carRentService, CartService cartService, UserService userService) {

        this.airplaneTicketsService = airplaneTicketsService;

        this.hotelService = hotelService;

        this.carRentService = carRentService;


        this.cartService = cartService;

        this.userService = userService;
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

    @ModelAttribute
    public AddAirplaneTicketsDTO allAirplaneTickets() {
        return new AddAirplaneTicketsDTO();
    }

    @ModelAttribute
    public AddCarsDTO allCars() {
        return new AddCarsDTO();
    }

    @ModelAttribute
    public AddHotelsDTO allHotels() {
        return new AddHotelsDTO();
    }


    @GetMapping("/add-offer")
    public String addOffer() {
        return "add-offer";
    }

    // TODO: should add another columns in the html page view
    @GetMapping("/airplane-tickets")
    public String getAirplaneTickets(Model model) {

        model.addAttribute("allAirplaneTickets", this.airplaneTicketsService.getAllAirplaneTickets());

        return "airplane-tickets";
    }

    @GetMapping("/cars")
    public String getCars(Model model) {
        model.addAttribute("allCars", this.carRentService.getAllCars());
        return "cars";
    }

    @GetMapping("/hotels")
    public String getHotels(Model model) {
        model.addAttribute("allHotels", this.hotelService.getAllHotels());
        return "hotels";
    }

    @GetMapping("/read-airplane-ticket-offer/{id}")
    public String getReadAirplaneTicketOffer(Model model, @PathVariable Long id) {
        AirplaneTicket airplaneTicket = airplaneTicketsService.getAirplaneTicketById(id);
        model.addAttribute("readAirplaneTicket", airplaneTicket);

        return "read-airplane-ticket-offer";
    }

    @GetMapping("/read-airplane-ticket-offer/{id}/addToCart")
    public String buyReadAirplaneTicketOffer(@PathVariable Long id, Principal principal) {
        cartService.AddToCartAirplaneTicket(principal, id);

        return "successfully-added";
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
