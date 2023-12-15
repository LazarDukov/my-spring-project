package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.*;
import com.example.travelseeker.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.example.travelseeker.model.enums.UserRoleEnum.*;


@Service
public class InitService {
    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    private final SellerRepository sellerRepository;
    private final BuyerRepository buyersRepository;

    private final CartService cartService;
    private final CartRepository cartRepository;
    private final AdminRepository adminRepository;


    @Autowired
    public InitService(UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder, SellerRepository sellerRepository, BuyerRepository buyersRepository, CartService cartService, OffersService offersService, CartRepository cartRepository, AdminRepository adminRepository) {
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.sellerRepository = sellerRepository;
        this.buyersRepository = buyersRepository;

        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.adminRepository = adminRepository;
    }


    private void initRoles() {
        UserRole admin = new UserRole().setRole(ADMIN);
        UserRole buyer = new UserRole().setRole(BUYER);
        UserRole seller = new UserRole().setRole(SELLER);

        userRoleRepository.save(admin);
        userRoleRepository.save(buyer);
        userRoleRepository.save(seller);
    }

    @PostConstruct
    public void Init() {
        if (userRoleRepository.count() == 0) {
            initRoles();
        }

        if (adminRepository.count() == 0) {
            initAdminUsers();
        }
        if (buyersRepository.count() == 0) {
            initBuyerUsers();
        }
        if (sellerRepository.count() == 0) {
            initSellerUsers();
        }

    }

    public void initAdminUsers() {


        Admin userAdmin = new Admin();
        userAdmin.setUsername("AdminAdminov")
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setEmail("adminov@gmail.com")
                .setCountry("Bulgaria")
                .setAge(40)
                .setPassword(passwordEncoder.encode("lazar123"))
                .setRoles(new ArrayList<>());

        userAdmin.getRoles().add(userRoleRepository.findUserRoleByRole(ADMIN));


        adminRepository.saveAndFlush(userAdmin);
    }

    private void initBuyerUsers() {


        Buyer userBuyer = new Buyer();
        userBuyer.setUsername("BuyerBuyerov")
                .setFirstName("Buyer")
                .setLastName("Buyerov")
                .setEmail("Buyerov@gmail.com")
                .setCountry("Afganistan")
                .setAge(30)
                .setPassword(passwordEncoder.encode("lazar123"))
                .setRoles(new ArrayList<>());

        userBuyer.getRoles().add(userRoleRepository.findUserRoleByRole(BUYER));


        userBuyer.setCart(cartService.getNewCart());

        Cart cart = userBuyer.getCart();
        cart.setBuyer(userBuyer);
        buyersRepository.saveAndFlush(userBuyer);
        cartRepository.save(cart);
    }


    private void initSellerUsers() {


        Seller userSeller = new Seller();
        userSeller.setUsername("SellerSellerov")
                .setFirstName("Seller")
                .setLastName("Sellerov")
                .setEmail("sellerov@gmail.com")
                .setCountry("Afganistan")
                .setAge(60)
                .setPassword(passwordEncoder.encode("lazar123"))
                .setRoles(new ArrayList<>());

        userSeller.getRoles().add(userRoleRepository.findUserRoleByRole(SELLER));


        sellerRepository.saveAndFlush(userSeller);
    }


}

