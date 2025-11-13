package com.example.GearUp_GarageManagementSystem.models.dtos;

import com.example.GearUp_GarageManagementSystem.models.entities.CentralOffice;
import com.example.GearUp_GarageManagementSystem.models.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CentralOfficerRequestDTO  {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotBlank(message = "City is required")
    private String userCity;

    @NotBlank(message = "Address is required")
    private String userAddress;

    private String profileImageURL;

    @NotNull(message = "Central Office ID is required")
    private Long centralOfficeId;

}
