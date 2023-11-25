package com.example.travelseeker.util.validation;

import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.repository.BuyerRepository;
import com.example.travelseeker.repository.SellerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Email;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailValidator implements ConstraintValidator<EmailValidatorInterface, String> {

    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;


    @Autowired
    public EmailValidator(SellerRepository sellerRepository, BuyerRepository buyerRepository) {
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
    }
BeanWrapper
    @Override
    public void initialize(EmailValidatorInterface constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {


        Seller seller = this.sellerRepository.findFirstByEmail(email);
        Buyer buyer = this.buyerRepository.findFirstByEmail(email);
        if (seller == null && buyer == null) {
            return true;
        } else {
            return false;
        }
    }
}