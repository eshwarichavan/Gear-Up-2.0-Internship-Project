package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.execptions.CustomException;
import com.example.GearUp_GarageManagementSystem.models.dtos.StaffRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.StaffResponseDTO;
import com.example.GearUp_GarageManagementSystem.models.entities.Factory;
import com.example.GearUp_GarageManagementSystem.models.entities.Staff;
import com.example.GearUp_GarageManagementSystem.models.enums.Roles;
import com.example.GearUp_GarageManagementSystem.repositories.FactoryRepository;
import com.example.GearUp_GarageManagementSystem.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService{

    @Autowired
    private FactoryRepository factoryRepository;

    @Autowired
    private StaffRepository staffRepository;


    @Override
    public StaffResponseDTO addStaff(StaffRequestDTO dto) {
        Staff staff = new Staff();
        staff.setFullName(dto.getFullName());
        staff.setEmail(dto.getEmail());
        staff.setUserCity(dto.getUserCity());
        staff.setUserAddress(dto.getUserAddress());
        staff.setAssignedFactory(dto.getAssignedFactory());
        staff.setAssignedBay(dto.getAssignedBay());
        staff.setProfileImageURL(dto.getProfileImageURL());
        staff.setRole(dto.getRole());

        Staff saved = staffRepository.save(staff);

        return new StaffResponseDTO(
                saved.getId(),
                saved.getFullName(),
                saved.getEmail(),
                saved.getUserCity(),
                saved.getUserAddress(),
                saved.getAssignedFactory(),
                saved.getAssignedBay(),
                saved.getProfileImageURL(),
                saved.getRole()
        );
    }


    //  Get All Staff :
    @Override
    public List<StaffResponseDTO> getAllStaff() {
        return staffRepository.findAll().stream()
                .map(staff -> new StaffResponseDTO(
                        staff.getId(),
                        staff.getFullName(),
                        staff.getEmail(),
                        staff.getUserCity(),
                        staff.getUserAddress(),
                        staff.getAssignedFactory(),
                        staff.getAssignedBay(),
                        staff.getProfileImageURL(),
                        staff.getRole()
                ))
                .collect(Collectors.toList());
    }


    // Search Staff by Name :
    @Override
    public List<StaffResponseDTO> searchStaffByName(String name) {
        return staffRepository.findByFullNameContainingIgnoreCase(name).stream()
                .map(staff -> new StaffResponseDTO(
                        staff.getId(),
                        staff.getFullName(),
                        staff.getEmail(),
                        staff.getUserCity(),
                        staff.getUserAddress(),
                        staff.getAssignedFactory(),
                        staff.getAssignedBay(),
                        staff.getProfileImageURL(),
                        staff.getRole()
                ))
                .collect(Collectors.toList());
    }


    // Get Staff by Role :
    @Override
    public List<StaffResponseDTO> getStaffByRole(String role) {
        Roles enumRole;
        try {
            enumRole = Roles.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CustomException("Invalid role: " + role, HttpStatus.BAD_REQUEST);
        }

        List<Staff> staffList = staffRepository.findByRole(enumRole);

        return staffList.stream()
                .map(staff -> new StaffResponseDTO(
                        staff.getId(),
                        staff.getFullName(),
                        staff.getEmail(),
                        staff.getUserCity(),
                        staff.getUserAddress(),
                        staff.getAssignedFactory(),
                        staff.getAssignedBay(),
                        staff.getProfileImageURL(),
                        staff.getRole()
                ))
                .collect(Collectors.toList());
    }


    // Update Staff :
    @Override
    public StaffResponseDTO updateStaff(Long id, StaffRequestDTO dto) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new CustomException("Staff not found with ID: " + id, HttpStatus.NOT_FOUND));

        // Update staff fields
        staff.setFullName(dto.getFullName());
        staff.setEmail(dto.getEmail());
        staff.setUserCity(dto.getUserCity());
        staff.setUserAddress(dto.getUserAddress());
        staff.setAssignedFactory(dto.getAssignedFactory());
        staff.setAssignedBay(dto.getAssignedBay());
        staff.setProfileImageURL(dto.getProfileImageURL());
        staff.setRole(dto.getRole());

        Staff updated = staffRepository.save(staff);

        return new StaffResponseDTO(
                updated.getId(),
                updated.getFullName(),
                updated.getEmail(),
                updated.getUserCity(),
                updated.getUserAddress(),
                updated.getAssignedFactory(),
                updated.getAssignedBay(),
                updated.getProfileImageURL(),
                updated.getRole()
        );
    }



    // Delete Staff :
    @Override
    public String deleteStaff(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new CustomException("Staff not found with ID: " + id, HttpStatus.NOT_FOUND));

        staffRepository.delete(staff);
        return "Staff deleted successfully!";
    }


}
