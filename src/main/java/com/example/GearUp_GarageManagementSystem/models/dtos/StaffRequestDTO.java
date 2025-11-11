package com.example.GearUp_GarageManagementSystem.models.dtos;

import com.example.GearUp_GarageManagementSystem.models.enums.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffRequestDTO {

    @NotBlank(message = "Full name cannot be empty")
    private String fullName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "User city cannot be empty")
    private String userCity;

    @NotBlank(message = "User address cannot be empty")
    private String userAddress;

    @NotBlank(message = "Assigned factory cannot be empty")
    private String assignedFactory;

    @NotBlank(message = "Assigned bay cannot be empty")
    private String assignedBay;

    private String profileImageURL;

    @NotNull(message = "Role must not be null")
    private Roles role;
}
