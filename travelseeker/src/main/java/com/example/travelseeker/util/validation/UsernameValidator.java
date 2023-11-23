//package com.example.travelseeker.util.validation;
//
//import com.example.travelseeker.repository.BuyerRepository;
//import com.example.travelseeker.repository.SellerRepository;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class UsernameValidator implements ConstraintValidator<UsernameValidatorInterface, String> {
//
//    private final SellerRepository sellerRepository;
//    private final BuyerRepository buyerRepository;
//
//
//    @Autowired
//    public UsernameValidator(SellerRepository sellerRepository, BuyerRepository buyerRepository) {
//        this.sellerRepository = sellerRepository;
//        this.buyerRepository = buyerRepository;
//    }
//
//    @Override
//    public void initialize(UsernameValidatorInterface constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
//    }
//
//    @Override
//    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
//        if (this.sellerRepository.findSellerByUsername(username).isEmpty() && this.buyerRepository.findBuyerByUsername(username).isEmpty()) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }
//}