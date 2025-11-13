package com.example.GearUp_GarageManagementSystem.controllers;

import com.example.GearUp_GarageManagementSystem.models.dtos.*;
import com.example.GearUp_GarageManagementSystem.services.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('OWNER')")
@Tag(name = "Users API", description ="This is user controller API to manage all the CRUD operations of User.")  //for swagger
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/owner/create")
    public ResponseEntity<String> createUserByOwner(@RequestBody @Valid FactoryUserCreateRequestDTO dto) {
        try {
            userServiceImpl.createFactoryUser(dto);
            return ResponseEntity.ok("User created and email sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //Update User :
    @PutMapping("profile/update/{id}")
    @Operation(summary = "Updates the User Data")
    public ResponseEntity<ProfileResponseDTO> updateUser(
            @Valid
            @PathVariable Long id,
            @RequestBody ProfileRequestDTO dto){
        return ResponseEntity.ok(userServiceImpl.updateUser(id,dto));

    }


    //Delete User :
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "deletes the User Data")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userServiceImpl.deleteUser(id);
        return ResponseEntity.ok("User with Id : "+id + " \n Deleted Successfully..");
    }


    //Get User by Id :
    @GetMapping("/getById/{id}")
    @Operation(summary = "Get User by Id")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userServiceImpl.getUserById(id));
    }


    //Get All User :
    @GetMapping("/getAll")
    @Operation(summary = "Get all Users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userServiceImpl.getAllUsers();
        return ResponseEntity.ok(users);
    }

}

