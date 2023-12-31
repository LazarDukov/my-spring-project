package com.example.travelseeker.util.validation;

import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.repository.BuyerRepository;
import com.example.travelseeker.repository.SellerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UsernameValidator implements ConstraintValidator<UsernameValidatorInterface, String> {

    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;


    @Autowired
    public UsernameValidator(SellerRepository sellerRepository, BuyerRepository buyerRepository) {
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
    }

    @Override
    public void initialize(UsernameValidatorInterface constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {


        Seller seller = this.sellerRepository.findFirstByUsername(username);
        Buyer buyer = this.buyerRepository.findFirstByUsername(username);
        if (seller == null && buyer == null) {
            return true;
        } else {
            return false;
        }
    }
}