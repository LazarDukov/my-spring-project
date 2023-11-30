package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller getSellerByUsername(String username) {
        return sellerRepository.findSellerByUsername(username).orElse(null);

    }



}
