package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.Admin;
import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.model.entities.UserRole;
import com.example.travelseeker.repository.AdminRepository;
import com.example.travelseeker.repository.BuyerRepository;
import com.example.travelseeker.repository.SellerRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ApplicationUserDetailsService implements UserDetailsService {

    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;
    private final AdminRepository adminRepository;

    public ApplicationUserDetailsService(AdminRepository adminRepository, SellerRepository sellerRepository, BuyerRepository buyerRepository) {
        this.adminRepository = adminRepository;
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return sellerRepository.findSellerByUsername(username).map(this::mapSeller).orElseGet(() ->
                buyerRepository.findBuyerByUsername(username).map(this::mapBuyer).orElseGet(() ->
                        adminRepository.findAdminByUsername(username).map(this::mapAdmin)
                        .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found!"))));

    }

    private UserDetails mapAdmin(Admin admin) {
        return
                //използваме дефолтната имплементация на Spring
                org.springframework.security.core.userdetails.User.builder().
                        username(admin.getUsername()).
                        password(admin.getPassword()).
                        authorities(admin.
                                getRoles().
                                stream().map(this::map).
                                toList()).
                        build();
    }

    private UserDetails mapSeller(Seller seller) {
        return
                //използваме дефолтната имплементация на Spring
                org.springframework.security.core.userdetails.User.builder().
                        username(seller.getUsername()).
                        password(seller.getPassword()).
                        authorities(seller.
                                getRoles().
                                stream().map(this::map).
                                toList()).
                        build();
    }

    private UserDetails mapBuyer(Buyer buyer) {
        return
                //използваме дефолтната имплементация на Spring
                org.springframework.security.core.userdetails.User.builder().
                        username(buyer.getUsername()).
                        password(buyer.getPassword()).
                        authorities(buyer.
                                getRoles().
                                stream().map(this::map).
                                toList()).
                        build();
    }

    //обяснява на Spring какво може да прави юзъра с тази си роля
    private GrantedAuthority map(UserRole userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
                userRole.getRole().name());
    }
}
