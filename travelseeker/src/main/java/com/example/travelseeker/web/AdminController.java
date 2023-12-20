package com.example.travelseeker.web;

import com.example.travelseeker.model.dtos.AddAdminDTO;
import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller

public class AdminController {
    private final SellerService sellerService;
    private final BuyerService buyerService;
    private final AdminService adminService;

    private final AirplaneTicketsService airplaneTicketsService;

    private final CarRentService carRentService;
    private final HotelService hotelService;

    public AdminController(SellerService sellerService, BuyerService buyerService, AdminService adminService, AirplaneTicketsService airplaneTicketsService, CarRentService carRentService, HotelService hotelService) {
        this.sellerService = sellerService;
        this.buyerService = buyerService;
        this.adminService = adminService;
        this.airplaneTicketsService = airplaneTicketsService;
        this.carRentService = carRentService;
        this.hotelService = hotelService;
    }

    @ModelAttribute("createAdmin")
    public AddAdminDTO initUserRegistrationDTO() {
        return new AddAdminDTO();
    }

    @GetMapping("/users/all-users")
    public String getAllUsers(Model model) {
        List<Seller> sellers = sellerService.getAllSellers();
        List<Buyer> buyers = buyerService.getAllBuyers();

        model.addAttribute("allSellers", sellers);
        model.addAttribute("allBuyers", buyers);
        return "all-users";
    }

    @GetMapping("/users/create-admin")
    public String getCreateAdmin() {
        return "create-admin";
    }

    @PostMapping("/users/create-admin")
    public String createAdmin(@Valid AddAdminDTO addAdminDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createAdmin", addAdminDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createAdmin", bindingResult);
            return "redirect:/users/create-admin";
        }

        adminService.createNewAdmin(addAdminDTO);
        return "redirect:/users/home";
    }

    @GetMapping("/users/ban-seller/{id}")
    public String banSeller(@PathVariable UUID id) {
        sellerService.banSeller(id);
        return "redirect:/users/all-users";
    }

    @GetMapping("/users/ban-buyer/{id}")
    public String banBuyer(@PathVariable UUID id) {
        buyerService.banBuyer(id);

        return "redirect:/users/all-users";
    }

    @GetMapping("/offers/remove-airplane-ticket-by-admin/{id}")
    public String removeAirplaneTicketByAdmin(@PathVariable UUID id) {
        airplaneTicketsService.removeAirplaneTicketByAdmin(id);
        return "redirect:/offers/airplane-tickets";
    }
    @GetMapping("/offers/remove-car-rent-by-admin/{id}")
    public String removeCarRentByAdmin(@PathVariable UUID id) {
        carRentService.removeCarRentByAdmin(id);
        return "redirect:/offers/car-rents";
    }

    @GetMapping("/offers/remove-hotel-by-admin/{id}")
    public String removeHotelByAdmin(@PathVariable UUID id) {
        hotelService.removeHotelByAdmin(id);
        return "redirect:/offers/hotels";
    }
}
