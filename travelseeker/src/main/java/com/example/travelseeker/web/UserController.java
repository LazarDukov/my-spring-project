package com.example.travelseeker.web;


import com.example.travelseeker.model.dtos.EditProfileDTO;
import com.example.travelseeker.model.dtos.view.UserProfileView;
import com.example.travelseeker.model.entities.*;
import com.example.travelseeker.repository.HotelRepository;
import com.example.travelseeker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/users")
public class UserController {


    private final UserService userService;



    @Autowired
    public UserController(SellerService sellerService, BuyerService buyerService,
                          UserService userService, OffersService offersService, HotelRepository hotelRepository, CartService cartService, AirplaneTicketsService airplaneTicketsService, HotelService hotelService, CarRentService carRentService) {

        this.userService = userService;

    }


    @ModelAttribute("editProfileDTO")
    public EditProfileDTO editProfileDTO() {
        return new EditProfileDTO();
    }

    @GetMapping("/my-profile")
    public String getMyProfile(Model model, Principal principal) {
        UserProfileView userProfileView = userService.getUserProfileView(principal);
        model.addAttribute("user", userProfileView);
        return "my-profile";
    }


    @GetMapping("/edit-profile")
    private String getEditProfile(Model model, Principal principal) {
        UserProfileView userProfileView = userService.getUserProfileView(principal);
        model.addAttribute("user", userProfileView);
        return "edit-profile";
    }

    @PostMapping("/edit-profile")
    public String updateUserProfile(EditProfileDTO editProfileDTO, Principal principal, BindingResult
            bindingResult, RedirectAttributes redirectAttributes) {
        userService.editProfile(editProfileDTO, principal);
        //TODO: should create validations for edit profile

        return "redirect:/users/my-profile";
    }




}



