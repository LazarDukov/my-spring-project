package com.example.travelseeker.web;


import com.example.travelseeker.model.dtos.EditProfileDTO;
import com.example.travelseeker.model.dtos.view.UserProfileView;
import com.example.travelseeker.service.UserService;
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

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {


    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {

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
    public String updateUserProfile(@Valid EditProfileDTO editProfileDTO, Principal principal, BindingResult
            bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", editProfileDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            return "redirect:/users/edit-profile";
        }
        userService.editProfile(editProfileDTO, principal);
        return "redirect:/users/my-profile";
    }


}



