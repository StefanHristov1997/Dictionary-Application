package com.dictionaryapp.validation.validator;

import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.validation.annotation.PasswordMatcher;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class PasswordMatcherValidator implements ConstraintValidator<PasswordMatcher, UserRegisterDTO> {

    private String message;

    @Override
    public void initialize(PasswordMatcher constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(UserRegisterDTO userRegisterDTO, ConstraintValidatorContext context) {

        if (userRegisterDTO == null) {
            return true;
        } else {
            boolean isPasswordMatch = userRegisterDTO.password() != null && userRegisterDTO.password().equals(userRegisterDTO.confirmPassword());

            if (!isPasswordMatch) {
                HibernateConstraintValidatorContext hibernateContext =
                        context.unwrap(HibernateConstraintValidatorContext.class);

                hibernateContext
                        .buildConstraintViolationWithTemplate(message)
                        .addPropertyNode("confirmPassword")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
            }

            return isPasswordMatch;
        }
    }
}
