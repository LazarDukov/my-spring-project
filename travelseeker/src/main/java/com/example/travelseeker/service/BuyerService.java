package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.enums.UserRoleEnum;
import com.example.travelseeker.repository.BuyerRepository;
import com.example.travelseeker.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BuyerService {
    private final BuyerRepository buyerRepository;
    private final UserRoleRepository userRoleRepository;

    public BuyerService(BuyerRepository buyerRepository, UserRoleRepository userRoleRepository) {
        this.buyerRepository = buyerRepository;
        this.userRoleRepository = userRoleRepository;
    }



    public Buyer getBuyerByUsername(String username) {
        return buyerRepository.findBuyerByUsername(username).orElse(null);

    }


}
