package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.model.entities.UserRole;
import com.example.travelseeker.repository.BuyerRepository;
import com.example.travelseeker.repository.SellerRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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

        return sellerRepository.findSellerByUsername(username).map(this::mapSeller).orElseGet(() ->
                buyerRepository.findBuyerByUsername(username).map(this::mapBuyer)
                        .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found!")));

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
