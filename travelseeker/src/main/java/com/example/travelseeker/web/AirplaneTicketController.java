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

//    private final AirplaneTicketsService airplaneTicketsService;
//
//
//    @Autowired
//    public AirplaneTicketController(AirplaneTicketsService airplaneTicketsService, CartService cartService, UserService userService) {
//
//        this.airplaneTicketsService = airplaneTicketsService;
//
//
//    }
//
//    //TODO: Should check about the work of these two model attributes. One of them can be skipped.
//    @ModelAttribute("addAirplaneTicketsDTO")
//    public AddAirplaneTicketsDTO addAirplaneTicketsDTO() {
//        return new AddAirplaneTicketsDTO();
//    }
//
//
//    // TODO: should add another columns in the html page view
//    @GetMapping("/airplane-tickets")
//    public String getAirplaneTickets(Model model) {
//
//        model.addAttribute("allAirplaneTickets", this.airplaneTicketsService.getAllAirplaneTickets());
//
//        return "airplane-tickets";
//    }
//
//    @GetMapping("/add-airplane-tickets")
//    public String getAddAirplaneTickets() {
//        return "add-airplane-tickets";
//    }
//
//    @GetMapping("/read-airplane-ticket-offer/{id}")
//    public String getReadAirplaneTicketOffer(Model model, @PathVariable UUID id) {
//        AirplaneTicket airplaneTicket = airplaneTicketsService.getAirplaneTicketById(id);
//        model.addAttribute("readAirplaneTicket", airplaneTicket);
//
//        return "read-airplane-ticket-offer";
//    }
//
//
//    // TODO: should redirect to right page after successful adding airplane ticket, reformat DATE and adding some restrictions
//    @PostMapping("/add-airplane-tickets")
//    public String addAirplaneTickets(@Valid AddAirplaneTicketsDTO addAirplaneTicketsDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws ParseException {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("addAirplaneTicketsDTO", addAirplaneTicketsDTO);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addAirplaneTicketsDTO", bindingResult);
//            return "redirect:/offers/add-airplane-tickets";
//        }
//        airplaneTicketsService.addNewAirplaneTicket(addAirplaneTicketsDTO);
//        return "successfully-added";
//    }
}
