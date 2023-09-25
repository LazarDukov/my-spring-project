package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.UserRegistrationDTO;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserRegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserRegistrationService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
    public void registerNewUser(@Valid UserRegistrationDTO userRegistrationDTO) {

            User newUser = new User().setUsername(userRegistrationDTO.getUsername())
                    .setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()))
                    .setFirstName(userRegistrationDTO.getFirstName())
                    .setLastName(userRegistrationDTO.getLastName())
                    .setAge(userRegistrationDTO.getAge())
                    .setCountry(userRegistrationDTO.getCountry())
                    .setEmail(userRegistrationDTO.getEmail())
                    .setRoles(new ArrayList<>())
                    .setCart(new ArrayList<>())
                    .setBoughtOffers(new ArrayList<>());

            userRepository.save(newUser);

    }
}