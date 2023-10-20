package com.example.travelseeker.web;

import com.example.travelseeker.model.dtos.AddCarsDTO;
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
public class CarRentalController {
    private final AirplaneTicketsService airplaneTicketsService;


    private final HotelService hotelService;


    private final CarRentService carRentService;


    private final CartService cartService;

    private final UserService userService;

    @Autowired
    public CarRentalController(AirplaneTicketsService airplaneTicketsService, HotelService hotelService, CarRentService carRentService, CartService cartService, UserService userService) {

        this.airplaneTicketsService = airplaneTicketsService;

        this.hotelService = hotelService;

        this.carRentService = carRentService;


        this.cartService = cartService;

        this.userService = userService;
    }
    //TODO: Should check about the work of these two model attributes. One of them can be skipped.
    @ModelAttribute("addCarsDTO")
    public AddCarsDTO addCarsDTO() {
        return new AddCarsDTO();
    }

    @ModelAttribute
    public AddCarsDTO allCars() {
        return new AddCarsDTO();
    }

    // TODO: should add another columns in the html page view
    @GetMapping("/add-cars")
    public String getAddCars() {
        return "add-cars";
    }

    @GetMapping("/cars")
    public String getCars(Model model) {
        model.addAttribute("allCars", this.carRentService.getAllCars());
        return "cars";
    }

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
