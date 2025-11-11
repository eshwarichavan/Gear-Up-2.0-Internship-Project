package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.configs.LoginPasswordGenerator;
import com.example.GearUp_GarageManagementSystem.execptions.CustomException;
import com.example.GearUp_GarageManagementSystem.models.dtos.DistributorRegisterRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.DistributorRegisterResponseDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.SignInRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.SignInResponseDTO;
import com.example.GearUp_GarageManagementSystem.models.entities.RefreshToken;
import com.example.GearUp_GarageManagementSystem.models.entities.User;
import com.example.GearUp_GarageManagementSystem.models.enums.Roles;
import com.example.GearUp_GarageManagementSystem.repositories.UserRepository;
import com.example.GearUp_GarageManagementSystem.utils.JwtUtil;
import jakarta.validation.Valid;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class AuthServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RefreshTokenServiceImpl refreshTokenService;

    public DistributorRegisterResponseDTO registerDistributor(DistributorRegisterRequestDTO requestDTO){

        if(userRepository.existsByEmail(requestDTO.getEmail())){
            throw new CustomException("Distributor with this email already exists!", HttpStatus.CONFLICT);
        }

        //using builder pattern :
        User distributor= User.builder()
                .fullName(requestDTO.getFullName())
                .email(requestDTO.getEmail())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .roles(Roles.DISTRIBUTOR)
                .isActive(true)
                .isVerified(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();


        User saved=userRepository.save(distributor);

        // Generate JWT Token using email and role
        List<String> roles = Collections.singletonList(saved.getRoles().name());
        String token = jwtUtil.generateToken(saved.getEmail(), roles);


        return new DistributorRegisterResponseDTO(
                "Distributor Registered Successfully !",
                distributor.getEmail(),
                distributor.getRoles().name()
        );
    }


    //Sign In :
    public SignInResponseDTO signIn(@Valid SignInRequestDTO requestDTO) {

        User user = userRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new CustomException("User not found!", HttpStatus.NOT_FOUND));

        if (!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
            throw new CustomException("Invalid password!", HttpStatus.UNAUTHORIZED);
        }

       //Generate token using user's email and role
        List<String> roles = Collections.singletonList(user.getRoles().name());
        String token = jwtUtil.generateToken(user.getEmail(), roles);
        System.out.println("Generated JWT Token : " + token);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());


//        String roleName = user.getRoles().name();
//        String accessToken = jwtUtil.generateToken(user.getEmail(), Collections.singletonList(roleName));
//        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

        return new SignInResponseDTO(
                user.getEmail(),
                user.getRoles(),
                "Login Successfully",
                token,
                refreshToken.getToken()
        );
    }


    //Email notification Login :
    public void registerSystemUser(String fullName, String email, Roles role) {

        if (userRepository.existsByEmail(email)) {
            throw new CustomException("User already exists with this email!", HttpStatus.CONFLICT);
        }

        // Generate random 8-char system password
        String generatedPassword = UUID.randomUUID().toString().substring(0, 8);

        User newUser = User.builder()
                .fullName(fullName)
                .email(email)
                .password(passwordEncoder.encode(generatedPassword))
                .roles(role)
                .isActive(true)
                .isVerified(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(newUser);

        // Send login credentials via email
        String subject = "Welcome to GearUp — Your Account is Ready!";
        String body = "Hey " + fullName + ",\n\n" +
                "Your GearUp account has been created successfully.\n\n" +
                "Username: " + email + "\n" +
                "Password: " + generatedPassword + "\n\n" +
                "Please log in using the above credentials.\n\n" +
                "Best wishes,\nTeam GearUp";

        emailService.sendLoginCredentials(email,email,generatedPassword);
    }



    //gets the current user from jwtutil
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
