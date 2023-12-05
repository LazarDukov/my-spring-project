package com.example.travelseeker.web;

import com.example.travelseeker.model.dtos.AddHotelsDTO;
import com.example.travelseeker.model.entities.Hotel;
import com.example.travelseeker.repository.SellerRepository;
import com.example.travelseeker.service.HotelService;
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
public class HotelController {

    private final HotelService hotelService;
    private final SellerRepository sellerRepository;

    @Autowired
    public HotelController(HotelService hotelService, SellerRepository sellerRepository) {

        this.hotelService = hotelService;
        this.sellerRepository = sellerRepository;
    }


    @ModelAttribute("addHotelsDTO")
    public AddHotelsDTO addHotelsDTO() {
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

    @GetMapping("/view-hotel-offer/{id}")
    public String getViewAirplaneTicketOffer(Model model, @PathVariable UUID id) {
        Hotel hotel = hotelService.getHotelById(id);
        model.addAttribute("readHotel", hotel);

        return "view-hotel-offer";
    }

    // TODO: should redirect to right page after successful adding hotel and create restrictions in DTO

    @PostMapping("/add-hotels")
    public String addHotels(Principal principal, @Valid AddHotelsDTO addHotelsDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addHotelsDTO", addHotelsDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addHotelsDTO", bindingResult);
            return "redirect:/offers/add-hotels";
        }
        hotelService.addNewHotel(principal, addHotelsDTO);
        return "successfully-added";
    }

    @PostMapping("/remove-hotel/{id}")
    public String removeAirplaneTicket(Principal principal, @PathVariable UUID id) {
        hotelService.removePublishedHotel(principal, id);
        return "removed";
    }

}
