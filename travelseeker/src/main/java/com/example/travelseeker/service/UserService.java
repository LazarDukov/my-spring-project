package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.view.UserProfileView;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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





}
