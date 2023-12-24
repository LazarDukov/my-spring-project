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
import com.example.travelseeker.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
public class UserService {

    private final SellerService sellerService;
    private final BuyerService buyerService;
    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;

    private final AdminService adminService;
    private final AdminRepository adminRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserService(SellerService sellerService, BuyerService buyerService,
                       SellerRepository sellerRepository,
                       BuyerRepository buyerRepository,
                       AdminService adminService,
                       AdminRepository adminRepository,
                       UserRoleRepository userRoleRepository) {
        this.sellerService = sellerService;
        this.buyerService = buyerService;

        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
        this.adminService = adminService;
        this.adminRepository = adminRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public Seller getSeller(Principal principal) {

        return sellerRepository.findFirstByUsername(principal.getName());

    }

    public Buyer getBuyer(Principal principal) {
        return buyerRepository.findFirstByUsername(principal.getName());

    }

    public Admin getAdmin(Principal principal) {
        return adminRepository.findFirstByUsername(principal.getName());

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

    @Transactional
    public UserProfileView getUserProfileView(Principal principal) {

        Seller seller = getSeller(principal);
        Buyer buyer = getBuyer(principal);
        Admin admin = getAdmin(principal);
        String role = determineUserRole(seller, buyer, admin);


        if ("Seller".equals(role)) {
            return new UserProfileView(seller.getUsername(), seller.getFirstName(), seller.getLastName(), seller.getEmail(), seller.getAge(), "SELLER", seller.getCountry());
        } else if ("Buyer".equals(role)) {
            return new UserProfileView(buyer.getUsername(), buyer.getFirstName(), buyer.getLastName(), buyer.getEmail(), buyer.getAge(), "BUYER", buyer.getCountry());
        } else if ("Admin".equals(role)) {
            return new UserProfileView(admin.getUsername(), admin.getFirstName(), admin.getLastName(), admin.getEmail(), admin.getAge(), "ADMIN", admin.getCountry());
        } else {
            throw new IllegalStateException("Unknown user role");
        }
    }

    @Transactional
    public void editProfile(EditProfileDTO editProfileDTO, Principal principal) {
        Seller seller = getSeller(principal);
        Buyer buyer = getBuyer(principal);
        Admin admin = getAdmin(principal);
        String role = determineUserRole(seller, buyer, admin);
        if ("Seller".equals(role)) {
            updateProfile(seller, editProfileDTO);
            updateUsernameAndRefreshPrincipal(seller.getUsername(), new ApplicationUserDetailsService(adminRepository, sellerRepository, buyerRepository));
            sellerRepository.save(seller);


        } else if ("Buyer".equals(role)) {
            updateProfile(buyer, editProfileDTO);
            updateUsernameAndRefreshPrincipal(buyer.getUsername(), new ApplicationUserDetailsService(adminRepository, sellerRepository, buyerRepository));
            buyerRepository.save(buyer);
        } else if ("Admin".equals(role)) {
            updateProfile(admin, editProfileDTO);
            updateUsernameAndRefreshPrincipal(admin.getUsername(), new ApplicationUserDetailsService(adminRepository, sellerRepository, buyerRepository));
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

    public void updateUsernameAndRefreshPrincipal(String newUsername, ApplicationUserDetailsService applicationUserDetailsService) {
        UserDetails updatedUserDetails = applicationUserDetailsService.loadUserByUsername(newUsername);
        Authentication updatedAuthenticationToken = new UsernamePasswordAuthenticationToken(updatedUserDetails, null,
                updatedUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(updatedAuthenticationToken);
    }

}
