package com.example.travelseeker.service;

import com.example.travelseeker.model.dtos.EditProfileDTO;
import com.example.travelseeker.model.dtos.view.UserProfileView;
import com.example.travelseeker.model.entities.Admin;
import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.repository.AdminRepository;
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

    private final AdminService adminService;
    private final AdminRepository adminRepository;

    @Autowired
    public UserService(SellerService sellerService, BuyerService buyerService,
                       SellerRepository sellerRepository, BuyerRepository buyerRepository, AdminService adminService, AdminRepository adminRepository) {
        this.sellerService = sellerService;
        this.buyerService = buyerService;

        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
        this.adminService = adminService;
        this.adminRepository = adminRepository;
    }

    public UserProfileView getUserProfileView(Principal principal) {

        Seller seller = sellerService.getSellerByUsername(principal.getName());
        Buyer buyer = buyerService.getBuyerByUsername(principal.getName());
        Admin admin = adminService.getAdminByUsername(principal.getName());
        String role = determineUserRole(seller, buyer, admin);

        if ("Seller".equals(role)) {
            return new UserProfileView(seller.getUsername(), seller.getFirstName(), seller.getLastName(), seller.getEmail(), seller.getAge(), role, seller.getCountry());
        } else if ("Buyer".equals(role)) {
            return new UserProfileView(buyer.getUsername(), buyer.getFirstName(), buyer.getLastName(), buyer.getEmail(), buyer.getAge(), role, buyer.getCountry());
        } else if ("Admin".equals(role)) {
            return new UserProfileView(admin.getUsername(), admin.getFirstName(), admin.getLastName(), admin.getEmail(), admin.getAge(), role, admin.getCountry());
        } else {
            throw new IllegalStateException("Unknown user role");

        }
    }

    private String determineUserRole(Seller seller, Buyer buyer, Admin admin) {
        if (seller != null) {
            return "Seller";
        } else if (buyer != null) {
            return "Buyer";
        } else if (admin != null) {
            return "Admin";

        } else {

            return "Unknown";
        }
    }


    public void editProfile(EditProfileDTO editProfileDTO, Principal principal) {
        Seller seller = sellerService.getSellerByUsername(principal.getName());
        Buyer buyer = buyerService.getBuyerByUsername(principal.getName());
        Admin admin = adminService.getAdminByUsername(principal.getName());
        String role = determineUserRole(seller, buyer, admin);

        if ("Seller".equals(role)) {
            updateProfile(seller, editProfileDTO);
            sellerRepository.save(seller);
        } else if ("Buyer".equals(role)) {
            updateProfile(buyer, editProfileDTO);
            buyerRepository.save(buyer);
        } else if ("Admin".equals(role)) {
            updateProfile(admin, editProfileDTO);
            adminRepository.save(admin);
        } else {
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
