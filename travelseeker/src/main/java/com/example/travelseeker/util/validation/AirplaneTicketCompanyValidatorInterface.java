package com.example.travelseeker.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = AirplaneTicketCompanyValidator.class)
public @interface AirplaneTicketCompanyValidatorInterface {

    String message() default "Company name is invalid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}