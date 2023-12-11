package com.example.travelseeker.web;

import com.example.travelseeker.model.dtos.AddAirplaneTicketsDTO;
import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.service.AirplaneTicketsService;
import com.example.travelseeker.service.CartService;
import com.example.travelseeker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.text.ParseException;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class AirplaneTicketController {

    private final AirplaneTicketsService airplaneTicketsService;


    @Autowired
    public AirplaneTicketController(AirplaneTicketsService airplaneTicketsService, CartService cartService, UserService userService) {

        this.airplaneTicketsService = airplaneTicketsService;


    }

    @ModelAttribute("addAirplaneTicketsDTO")
    public AddAirplaneTicketsDTO addAirplaneTicketsDTO() {
        return new AddAirplaneTicketsDTO();
    }


    @GetMapping("/airplane-tickets")
    public String getAirplaneTickets(Model model) {

        model.addAttribute("allAirplaneTickets", this.airplaneTicketsService.getAllAirplaneTickets());

        return "airplane-tickets";
    }

    @GetMapping("/add-airplane-tickets")
    public String getAddAirplaneTickets() {
        return "add-airplane-tickets";
    }

    @GetMapping("/view-airplane-ticket-offer/{id}")
    public String getViewAirplaneTicketOffer(Model model, @PathVariable UUID id) {
        AirplaneTicket airplaneTicket = airplaneTicketsService.getAirplaneTicketById(id);
        model.addAttribute("readAirplaneTicket", airplaneTicket);

        return "view-airplane-ticket-offer";
    }


    @PostMapping("/add-airplane-tickets")
    public String addAirplaneTickets(Principal principal, @Valid AddAirplaneTicketsDTO addAirplaneTicketsDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws ParseException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addAirplaneTicketsDTO", addAirplaneTicketsDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addAirplaneTicketsDTO", bindingResult);
            return "redirect:/offers/add-airplane-tickets";
        }
        airplaneTicketsService.addNewAirplaneTicket(principal,addAirplaneTicketsDTO);
        return "successfully-added";
    }

    @GetMapping("/remove-airplane-ticket/{id}")
    public String removeAirplaneTicket(Principal principal, @PathVariable UUID id) {
        airplaneTicketsService.removePublishedAirplaneTicket(principal, id);
        return "removed";
    }

}
