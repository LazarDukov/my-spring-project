package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.EditProfileDTO;
import com.example.travelseeker.model.dtos.view.UserProfileView;
import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.repository.BuyerRepository;
import com.example.travelseeker.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {

    private final SellerService sellerService;
    private final BuyerService buyerService;
    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;

    @Autowired
    public UserService(SellerService sellerService, BuyerService buyerService,
                       SellerRepository sellerRepository, BuyerRepository buyerRepository) {
        this.sellerService = sellerService;
        this.buyerService = buyerService;

        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
    }

    public UserProfileView getUserProfileView(Principal principal) {

        Seller seller = sellerService.getSellerByUsername(principal.getName());
        Buyer buyer = buyerService.getBuyerByUsername(principal.getName());
        String role = determineUserRole(seller, buyer);

        if ("Seller".equals(role)) {
            return new UserProfileView(seller.getUsername(), seller.getFirstName(), seller.getLastName(), seller.getEmail(), seller.getAge(), role, seller.getCountry());
        } else if ("Buyer".equals(role)) {
            return new UserProfileView(buyer.getUsername(), buyer.getFirstName(), buyer.getLastName(), buyer.getEmail(), buyer.getAge(), role, buyer.getCountry());
        } else {
            throw new IllegalStateException("Unknown user role");
        }
    }

    private String determineUserRole(Seller seller, Buyer buyer) {
        if (seller != null) {
            return "Seller";
        } else if (buyer != null) {
            return "Buyer";
        } else {
            // Handle the case where the user is not found as either a seller or a buyer
            return "Unknown";
        }
    }


    public void editProfile(EditProfileDTO editProfileDTO, Principal principal) {
        Seller seller = sellerService.getSellerByUsername(principal.getName());
        Buyer buyer = buyerService.getBuyerByUsername(principal.getName());
        String role = determineUserRole(seller, buyer);

        if ("Seller".equals(role)) {
            updateProfile(seller, editProfileDTO);
            sellerRepository.save(seller);
        } else if ("Buyer".equals(role)) {
            updateProfile(buyer, editProfileDTO);
            buyerRepository.save(buyer);
        } else {
            // Handle the case where the user's role is neither seller nor buyer
            // You might want to throw an exception or handle it based on your application logic.
            throw new IllegalStateException("Unknown user role");
        }
    }

    private void updateProfile(User user, EditProfileDTO editProfileDTO) {
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
    }
}
