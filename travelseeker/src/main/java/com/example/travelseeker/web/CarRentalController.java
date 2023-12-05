package com.example.travelseeker.web;

import com.example.travelseeker.model.dtos.AddCarsDTO;
import com.example.travelseeker.model.entities.CarRent;
import com.example.travelseeker.service.CarRentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class CarRentalController {

    private final CarRentService carRentService;

    @Autowired
    public CarRentalController(CarRentService carRentService) {
        this.carRentService = carRentService;

    }

    @ModelAttribute("addCarsDTO")
    public AddCarsDTO addCarsDTO() {
        return new AddCarsDTO();
    }



    @GetMapping("/add-cars")
    public String getAddCars() {
        return "add-cars";
    }

    @GetMapping("/cars")
    public String getCars(Model model) {
        model.addAttribute("allCars", this.carRentService.getAllCars());
        return "cars";
    }

    @GetMapping("/view-car-offer/{id}")
    public String getViewCarOffer(Model model, @PathVariable UUID id) {
        CarRent car = carRentService.getCarRentById(id);
        model.addAttribute("readCar", car);

        return "view-car-offer";
    }


    @PostMapping("/add-cars")
    public String addCars( Principal principal, @Valid AddCarsDTO addCarsDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addCarsDTO", addCarsDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addCarsDTO", bindingResult);
            return "redirect:/offers/add-cars";
        }
        carRentService.addNewCar(principal,addCarsDTO);
        return "successfully-added";
    }
    @PostMapping("/remove-car-rent/{id}")
    public String removeCar(Principal principal,@PathVariable UUID id) {
        carRentService.removePublishedCarRent(principal, id);
        return "removed";
    }

}
