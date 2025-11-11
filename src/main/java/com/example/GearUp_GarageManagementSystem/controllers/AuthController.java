package com.example.GearUp_GarageManagementSystem.controllers;

import com.example.GearUp_GarageManagementSystem.models.dtos.DistributorRegisterRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.DistributorRegisterResponseDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.SignInRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.SignInResponseDTO;
import com.example.GearUp_GarageManagementSystem.repositories.RefreshTokenRepository;
import com.example.GearUp_GarageManagementSystem.services.AuthService;
import com.example.GearUp_GarageManagementSystem.services.AuthServiceImpl;
import com.example.GearUp_GarageManagementSystem.services.RefreshTokenServiceImpl;
import com.example.GearUp_GarageManagementSystem.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication API", description ="This is Auth controller API to manage all the operations of Authentication.")  //for swagger
@SecurityRequirement(name = "bearerAuth") // tells Swagger this endpoint requires auth
@Validated
public class AuthController {

    @Autowired
    private AuthServiceImpl authServiceimpl;

    @Autowired
    private RefreshTokenServiceImpl refreshTokenService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/signup/distributor")
    @Operation(summary = "Distributor Sign Up ")
    public ResponseEntity<DistributorRegisterResponseDTO> registerDistributor(
            @Valid
            @RequestBody DistributorRegisterRequestDTO requestDTO) {

        return ResponseEntity.ok(authServiceimpl.registerDistributor(requestDTO));
    }


    @PostMapping("/signin")
    @Operation(summary = "Sign In api for all the Users")
    public ResponseEntity<SignInResponseDTO> signIn(
            @RequestBody SignInRequestDTO requestDTO) {

        return ResponseEntity.ok(authServiceimpl.signIn(requestDTO));
    }



    //Refresh Token :
    @PostMapping("/refreshToken")
    @Operation(summary = "Generates refresh token")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> payload){

        String requestToken = payload.get("refreshToken");

        return refreshTokenRepository.findByToken(requestToken)
                .map(token -> {
                    if (refreshTokenService.isTokenExpired(token)) {
                        refreshTokenRepository.delete(token);
                        return ResponseEntity.badRequest().body("Refresh token expired. Please login again.");
                    }
                    List<String> rolesList = new ArrayList<>();
                    rolesList.add(String.valueOf(token.getUser().getRoles()));
                    String newJwt = jwtUtil.generateToken(token.getUser().getEmail(), rolesList);
                    return ResponseEntity.ok(Map.of("token", newJwt));
                })
                .orElse(ResponseEntity.badRequest().body("Invalid refresh token."));
    }


    @PostMapping("/logout")
    @Operation(summary = "User will logout using this API")
    public ResponseEntity<?> logoutUser(@RequestBody Map<String, String> payload) {
        String requestToken = payload.get("refreshToken");

        if (requestToken == null || requestToken.isBlank()) {
            return ResponseEntity.badRequest().body("Refresh token is required.");
        }

        return refreshTokenRepository.findByToken(requestToken)
                .map(token -> {
                    refreshTokenRepository.delete(token);
                    return ResponseEntity.ok("Logged out successfully.");
                })
                .orElse(ResponseEntity.badRequest().body("Invalid refresh token."));
    }
}



