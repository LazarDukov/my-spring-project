package com.example.travelseeker.web;

import com.example.travelseeker.model.dtos.AddHotelsDTO;
import com.example.travelseeker.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/offers")
public class HotelController {
    private final AirplaneTicketsService airplaneTicketsService;


    private final HotelService hotelService;


    private final CarRentService carRentService;


    private final CartService cartService;

    private final UserService userService;

    @Autowired
    public HotelController(AirplaneTicketsService airplaneTicketsService, HotelService hotelService, CarRentService carRentService, CartService cartService, UserService userService) {

        this.airplaneTicketsService = airplaneTicketsService;

        this.hotelService = hotelService;

        this.carRentService = carRentService;


        this.cartService = cartService;

        this.userService = userService;
    }

//TODO: Should check about the work of these two model attributes. One of them can be skipped.

    @ModelAttribute("addHotelsDTO")
    public AddHotelsDTO addHotelsDTO() {
        return new AddHotelsDTO();
    }


    @ModelAttribute
    public AddHotelsDTO allHotels() {
        return new AddHotelsDTO();
    }

    @GetMapping("/hotels")
    public String getHotels(Model model) {
        model.addAttribute("allHotels", this.hotelService.getAllHotels());
        return "hotels";
    }


    @GetMapping("/add-hotels")
    public String getAddHotels() {
        return "add-hotels";
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


}
