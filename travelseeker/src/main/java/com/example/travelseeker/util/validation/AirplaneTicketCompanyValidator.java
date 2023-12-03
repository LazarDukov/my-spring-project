package com.example.travelseeker.util.validation;

import com.example.travelseeker.repository.AirplaneTicketsRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AirplaneTicketCompanyValidator implements ConstraintValidator<AirplaneTicketCompanyValidatorInterface, String> {

    private final AirplaneTicketsRepository airplaneTicketsRepository;

    public AirplaneTicketCompanyValidator(AirplaneTicketsRepository airplaneTicketsRepository) {
        this.airplaneTicketsRepository = airplaneTicketsRepository;
    }


    @Override
    public boolean isValid(String companyName, ConstraintValidatorContext constraintValidatorContext) {
        return !companyName.trim().isBlank() && companyName.length() >= 2;
    }
}
