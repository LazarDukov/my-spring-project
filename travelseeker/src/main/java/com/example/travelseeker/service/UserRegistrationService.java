package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.UserRegistrationDTO;
import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.Cart;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.model.enums.UserRoleEnum;
import com.example.travelseeker.repository.BuyerRepository;
import com.example.travelseeker.repository.CartRepository;
import com.example.travelseeker.repository.SellerRepository;
import com.example.travelseeker.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRegistrationService {
    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRoleRepository userRoleRepository;

    private final CartService cartService;
    private final CartRepository cartRepository;


    @Autowired
    public UserRegistrationService(SellerRepository sellerRepository, BuyerRepository buyerRepository, PasswordEncoder passwordEncoder,
                                   UserRoleRepository userRoleRepository, CartService cartService, CartRepository cartRepository) {
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;

        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;

        this.cartService = cartService;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public void registerNewUser(@Valid UserRegistrationDTO userRegistrationDTO) {
        if (userRegistrationDTO.getRole().equals("SELLER")) {
            registerSeller(userRegistrationDTO);

        } else {
            registerBuyer(userRegistrationDTO);
        }
    }

    public void registerSeller(UserRegistrationDTO userRegistrationDTO) {

        Seller newSeller = new Seller();
        newSeller.setUsername(userRegistrationDTO.getUsername())
                .setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()))
                .setFirstName("")
                .setLastName("")
                .setEmail(userRegistrationDTO.getEmail())
                .setCountry("")
                .setAge(0)
                .setRoles(userRoleRepository.findUserRoleByRole(UserRoleEnum.SELLER));

        sellerRepository.save(newSeller);
    }


    public void registerBuyer(UserRegistrationDTO userRegistrationDTO) {
        Buyer newBuyer = new Buyer();
        newBuyer.setUsername(userRegistrationDTO.getUsername())
                .setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()))
                .setFirstName("")
                .setLastName("")
                .setEmail(userRegistrationDTO.getEmail())
                .setCountry("")
                .setAge(0).setRoles(userRoleRepository.findUserRoleByRole(UserRoleEnum.BUYER));
        newBuyer.setCart(cartService.getNewCart());
        Cart cart = newBuyer.getCart();
        cart.setBuyer(newBuyer);


        buyerRepository.save(newBuyer);

        cartRepository.save(cart);

    }

    private boolean isUsernameExists(String username) {
        Optional<Seller> sellerOptional = sellerRepository.findSellerByUsername(username);
        if (sellerOptional.isPresent()) {
            return true;
        }
        Optional<Buyer> buyerOptional = buyerRepository.findBuyerByUsername(username);
        if (buyerOptional.isPresent()) {
            return true;
        }
        return false;

    }

    private boolean isEmailExists(String email) {
        Optional<Seller> sellerOptional = sellerRepository.findSellerByEmail(email);
        if (sellerOptional.isPresent()) {
            return true;
        }
        Optional<Buyer> buyerOptional = buyerRepository.findBuyerByEmail(email);
        if (buyerOptional.isPresent()) {
            return true;
        }
        return false;

    }


}
