package com.example.travelseeker.web;

import com.example.travelseeker.model.dtos.AddHotelsDTO;
import com.example.travelseeker.model.entities.Hotel;
import com.example.travelseeker.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

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

    @GetMapping("/read-hotel-offer/{id}")
    public String getReadAirplaneTicketOffer(Model model, @PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        model.addAttribute("readHotel", hotel);

        return "read-hotel-offer";
    }

    // TODO: should redirect to right page after successful adding hotel and create restrictions in DTO

    @PostMapping("/add-hotels")
    public String addHotels(@Valid AddHotelsDTO addHotelsDTO, Principal principal, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addHotelsDTO", addHotelsDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addHotelsDTO", bindingResult);
            return "redirect:/offers/home";
        }
        hotelService.addNewHotel(addHotelsDTO, principal);
        return "successfully-added";
    }

    @GetMapping("/read-hotel-offer/{id}/addToCart")
    public String buyReadHotelOffer(@PathVariable Long id, Principal principal) {
        cartService.AddToCartHotel(principal, id);

        return "successfully-added";
    }


}
