package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.model.entities.UserRole;
import com.example.travelseeker.repository.BuyerRepository;
import com.example.travelseeker.repository.SellerRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ApplicationUserDetailsService implements UserDetailsService {

    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;

    public ApplicationUserDetailsService(SellerRepository sellerRepository, BuyerRepository buyerRepository) {
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Seller> seller = sellerRepository.findSellerByUsername(username);
        if (seller.isPresent()) {
            return mapSeller(seller.get());
        } else {
            // If Seller not found, try to find or create Buyer
            Optional<Buyer> buyer = buyerRepository.findBuyerByUsername(username);
            return buyer.map(this::mapBuyer).orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));
        }
    }

    private UserDetails mapSeller(Seller seller) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(seller.getUsername())
                .password(seller.getPassword())
                .authorities(extractGrantedAuthority(seller))
                .build();
    }

    private UserDetails mapBuyer(Buyer buyer) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(buyer.getUsername())
                .password(buyer.getPassword())
                .authorities(extractGrantedAuthority(buyer))
                .build();
    }


    private GrantedAuthority extractGrantedAuthority(Seller seller) {
        return new SimpleGrantedAuthority("ROLE_" + seller.getRole());
    }

    private GrantedAuthority extractGrantedAuthority(Buyer buyer) {
        return new SimpleGrantedAuthority("ROLE_" + buyer.getRole());
    }

}