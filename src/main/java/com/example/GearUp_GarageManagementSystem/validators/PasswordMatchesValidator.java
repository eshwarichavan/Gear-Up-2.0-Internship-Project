package com.example.GearUp_GarageManagementSystem.validators;

import com.example.GearUp_GarageManagementSystem.models.dtos.DistributorRegisterRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, DistributorRegisterRequestDTO> {
    @Override
    public boolean isValid(DistributorRegisterRequestDTO signUpRequestDTO, ConstraintValidatorContext constraintValidatorContext) {

        if (signUpRequestDTO.getPassword() == null || signUpRequestDTO.getConfirmPassword() == null) {
            return false;
        }

        return signUpRequestDTO.getPassword().equals(signUpRequestDTO.getConfirmPassword());
    }

}
