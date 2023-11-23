package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.repository.BuyerRepository;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {
    private final BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public Buyer getBuyerByUsername(String username) {
        return buyerRepository.findBuyerByUsername(username).orElse(null);

    }

}
