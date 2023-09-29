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
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public User getUserByName(String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }

    public UserProfileView getUserProfileViewByName(String username) {
        return mapToUserProfileView(getUserByName(username));
    }

    private UserProfileView mapToUserProfileView(User userByName) {
        return this.modelMapper.map(userByName, UserProfileView.class);
    }


}
