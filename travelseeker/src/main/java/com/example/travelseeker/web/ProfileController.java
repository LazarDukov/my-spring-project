package com.example.travelseeker.web;


import com.example.travelseeker.model.dtos.view.UserProfileView;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/my-profile")
    private String getMyProfile(Model model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        UserProfileView userProfileView = new UserProfileView(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAge(), user.getRoleAsString(), user.getCountry());
        model.addAttribute("user", userProfileView);
        return "my-profile";
    }
}
