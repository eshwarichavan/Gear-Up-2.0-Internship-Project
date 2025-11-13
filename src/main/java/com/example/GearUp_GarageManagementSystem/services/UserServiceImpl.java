package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.execptions.CustomException;
import com.example.GearUp_GarageManagementSystem.models.dtos.*;
import com.example.GearUp_GarageManagementSystem.models.entities.User;
import com.example.GearUp_GarageManagementSystem.models.enums.Roles;
import com.example.GearUp_GarageManagementSystem.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;


    //Create User :
    public void createFactoryUser(FactoryUserCreateRequestDTO userDTO) {
        // 1. Generate random password
        String rawPassword = generateRandomPassword();

        // 2. Create user entity
        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());
        user.setProfileImageURL(userDTO.getProfileImageURL());
        user.setRoles(Roles.valueOf(userDTO.getRole()));
        user.setAssignedFactory(userDTO.getAssignedFactory());
        user.setPassword(passwordEncoder.encode(rawPassword));

        userRepository.save(user);

        // 3. Send email
        emailService.sendLoginCredentials(
                userDTO.getEmail(),
                "Your Factory Login Credentials",
                "Hi " + userDTO.getFullName() + ",\n\n" +
                        "Your account has been created for factory : " + userDTO.getAssignedFactory() + ".\n \n" +
                        "Email: " + userDTO.getEmail() + "\n" +
                        "Password: " + rawPassword + "\n\n" +
                        "Please log in using above credentials."
        );
    }

    private String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }


    //Update User :
    public ProfileResponseDTO updateUser(Long id, ProfileRequestDTO requestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found with Id : " + id , HttpStatus.NOT_FOUND));


        user.setFullName(requestDTO.getFullName());
        user.setEmail(requestDTO.getEmail());
        user.setAddress(requestDTO.getAddress());
        user.setProfileImageURL(requestDTO.getProfileImageURL());

        if(requestDTO.getPassword() != null && !requestDTO.getPassword().isEmpty()){
            user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        }
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
        return mapToProfileResponse(user);
    }


    //Delete User :
    public void deleteUser(Long id) {

        if(!userRepository.existsById(id)) {
            throw new CustomException("User Not found with Id : " + id ,HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
    }


    //Get User By id :
    public UserResponseDTO getUserById(Long id){
        User user=userRepository.findById(id)
                .orElseThrow(()-> new CustomException("User Not Found with Id : "+ id ,HttpStatus.NOT_FOUND));
        return mapToResponse(user);
    }


    //Get all users :
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }



    //Object mapper  :
    public UserResponseDTO mapToResponse(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRoles().name(),
                user.isActive(),
                user.isVerified(),
                user.getProfileImageURL()
        );
    }

    public ProfileResponseDTO mapToProfileResponse(User user) {
        return new ProfileResponseDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getProfileImageURL(),
                user.getAddress(),
                user.getPassword()
        );
    }

    private UserSummaryDTO mapToSummaryDTO(User user) {
        return new UserSummaryDTO(
                user.getFullName(),
                user.getEmail(),
                user.getRoles().name()
        );
    }
}


