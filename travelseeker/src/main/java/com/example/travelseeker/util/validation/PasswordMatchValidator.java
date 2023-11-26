package com.example.travelseeker.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatchValidatorInterface, Object> {

    private String password;
    private String confirmPassword;

    @Override
    public void initialize(PasswordMatchValidatorInterface constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.confirmPassword = constraintAnnotation.confirmPassword();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(object);

        Object passwordValue = beanWrapper.getPropertyValue(this.password);
        Object confirmPasswordValue = beanWrapper.getPropertyValue(this.confirmPassword);

        if (passwordValue == null || confirmPasswordValue == null) {
            // Handle null values if necessary
            return false;
        }

        // Compare the password and confirmPassword values
        boolean isValid = passwordValue.equals(confirmPasswordValue);

        if (!isValid) {
            // Customize the error message
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Passwords do not match")
                    .addPropertyNode(confirmPassword)
                    .addConstraintViolation();
        }

        return isValid;
    }
}
