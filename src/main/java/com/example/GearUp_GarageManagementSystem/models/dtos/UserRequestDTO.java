package com.example.GearUp_GarageManagementSystem.models.dtos;


import com.example.GearUp_GarageManagementSystem.models.enums.Roles;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Format")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@(gmail|yahoo|outlook)\\.com$",
            message = "Email must be like john@gmail.com"
    )
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=6,message = "Password must be atleast 6 character")
    private String password;


    private String address;
    private String profileImageURL;
    private Roles roles;

}
