package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.repositories.CentralOfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CentralServiceImpl {

    @Autowired
    private CentralOfficerRepository centralOfficerRepository;
}
