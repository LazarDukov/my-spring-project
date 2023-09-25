package com.example.travelseeker.web;

import com.example.travelseeker.model.dtos.UserRegistrationDTO;
import com.example.travelseeker.service.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    private final UserRegistrationService userRegistrationService;

    public RegisterController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }
    @ModelAttribute("registerDTO")
    public UserRegistrationDTO initUserRegistrationDTO(){
        return new UserRegistrationDTO();
    }
    @GetMapping("/users/register")
    public String getRegister() {
        return "auth-register";
    }

    @PostMapping("/users/register")
    public String registerUser(@Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);
            return "redirect:/users/register";
        }

        userRegistrationService.registerNewUser(userRegistrationDTO);
        return "redirect:/users/login";
    }

}
