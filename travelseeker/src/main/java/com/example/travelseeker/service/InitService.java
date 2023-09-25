package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.model.entities.UserRole;
import com.example.travelseeker.model.enums.UserRoleEnum;
import com.example.travelseeker.repository.UserRepository;
import com.example.travelseeker.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.travelseeker.model.enums.UserRoleEnum.*;


@Service
public class InitService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public InitService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @PostConstruct
    public void Init() {
        if (userRoleRepository.count() == 0) {
            initRoles();
        }
        if (userRepository.count() == 0) {
            initAdminUsers();
            initClientUsers();
            initSellerUsers();
        }
    }


    private void initRoles() {
        UserRole admin = new UserRole().setRole(ADMIN);
        UserRole client = new UserRole().setRole(CLIENT);
        UserRole seller = new UserRole().setRole(SELLER);

        userRoleRepository.save(admin);
        userRoleRepository.save(client);
        userRoleRepository.save(seller);
    }

    public void initAdminUsers() {


        User userAdmin = new User().setUsername("AdminAdminov")
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setEmail("adminov@gmail.com")
                .setCountry("Bulgaria")
                .setAge(40)
                .setPassword(passwordEncoder.encode("lazar123"))
                .setRoles(userRoleRepository.findUserRoleByRole(ADMIN));


        userRepository.saveAndFlush(userAdmin);
    }


    private void initClientUsers() {


        User userClient = new User().setUsername("ClientClientov")
                .setFirstName("Client")
                .setLastName("Client")
                .setEmail("clientov@gmail.com")
                .setCountry("Germany")
                .setAge(30)
                .setPassword(passwordEncoder.encode("lazar123"))
                .setRoles(userRoleRepository.findUserRoleByRole(CLIENT));


        userRepository.saveAndFlush(userClient);
    }


    private void initSellerUsers() {


        User userSeller = new User().setUsername("SellerSellerov")
                .setFirstName("Seller")
                .setLastName("Sellerov")
                .setEmail("sellerov@gmail.com")
                .setCountry("Afganistan")
                .setAge(60)
                .setPassword(passwordEncoder.encode("lazar123"))
                .setRoles(userRoleRepository.findUserRoleByRole(SELLER));


        userRepository.saveAndFlush(userSeller);
    }


}
