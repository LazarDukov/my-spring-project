package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.UserRegistrationDTO;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.repository.UserRepository;
import com.example.travelseeker.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final CartService cartService;


    private final BoughtOffersService boughtOffersService;

    @Autowired
    public UserRegistrationService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository, CartService cartService, BoughtOffersService boughtOffersService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.cartService = cartService;
        this.boughtOffersService = boughtOffersService;
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
                .setRoles(userRoleRepository.findUserRoleByRole(userRegistrationDTO.getRole()))
                .setCart(cartService.getNewCart())
                .setBoughtOffers(boughtOffersService.getNewBoughtOffers());


        userRepository.save(newUser);

    }

}