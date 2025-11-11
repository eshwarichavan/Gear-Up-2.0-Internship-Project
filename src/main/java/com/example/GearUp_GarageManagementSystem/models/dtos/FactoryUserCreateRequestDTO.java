package com.example.GearUp_GarageManagementSystem.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FactoryUserCreateRequestDTO  {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Factory registration number is required")
    private String assignedFactory;

    @NotBlank(message = "city is required")
    private String city;

    @NotBlank(message = "Address is required")
    private String address;

    private String profileImageURL;

    @NotBlank(message = "Role is required")
    private String role;  // Example: "PLANT_HEAD", "CENTRAL_OFFICER"
}
