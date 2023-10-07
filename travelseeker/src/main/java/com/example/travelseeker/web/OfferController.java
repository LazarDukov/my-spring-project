package com.example.travelseeker.web;

import com.example.travelseeker.model.dtos.AddAirplaneTicketsDTO;
import com.example.travelseeker.repository.AirplaneTicketsRepository;
import com.example.travelseeker.service.AirplaneTicketsService;
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

    @Autowired
    public OfferController(AirplaneTicketsRepository airplaneTicketsRepository, AirplaneTicketsService airplaneTicketsService) {
        this.airplaneTicketsRepository = airplaneTicketsRepository;
        this.airplaneTicketsService = airplaneTicketsService;
    }

    @ModelAttribute("addAirplaneTicketsDTO")
    public AddAirplaneTicketsDTO addAirplaneTicketsDTO() {
        return new AddAirplaneTicketsDTO();
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

    @GetMapping("/add-airplane-tickets")
    public String getAddAirplaneTickets() {
        return "add-airplane-tickets";
    }

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
}
