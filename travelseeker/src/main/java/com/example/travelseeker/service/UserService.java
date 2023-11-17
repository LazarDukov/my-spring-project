package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.EditProfileDTO;
import com.example.travelseeker.model.dtos.view.UserProfileView;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public User getUserByName(String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }


    public void editProfile(EditProfileDTO editProfileDTO, Principal principal) {
        User user = userRepository.findUserByUsername(principal.getName()).orElse(null);
        if (editProfileDTO.getUsername() != null) {
            user.setUsername(editProfileDTO.getUsername());
        }
        if (editProfileDTO.getFirstName() != null) {
            user.setFirstName(editProfileDTO.getFirstName());
        }
        if (editProfileDTO.getLastName() != null) {
            user.setLastName(editProfileDTO.getLastName());
        }
        if (editProfileDTO.getAge() > 0) {
            user.setAge(editProfileDTO.getAge());
        }
        if (editProfileDTO.getCountry() != null) {
            user.setCountry(editProfileDTO.getCountry());
        }
        if (editProfileDTO.getEmail() != null) {
            user.setEmail(editProfileDTO.getEmail());
        }
        userRepository.save(user);
    }
}
