package com.example.GearUp_GarageManagementSystem.configs;

import com.example.GearUp_GarageManagementSystem.models.entities.CentralOffice;
import com.example.GearUp_GarageManagementSystem.models.entities.User;
import com.example.GearUp_GarageManagementSystem.models.enums.Roles;
import com.example.GearUp_GarageManagementSystem.repositories.CentralOfficeRepository;
import com.example.GearUp_GarageManagementSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer implements CommandLineRunner {

    //Creating seeded Manufacturer here :

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CentralOfficeRepository centralOfficeRepository;

    @Override
    public void run(String... args) throws Exception {

        //Owner's default email id :
        String defaultManufacturerEmail = "eshudon@gmail.com";

        if (userRepository.findByEmail(defaultManufacturerEmail).isEmpty()) {

            User owner = new User();

            owner.setFullName("Default Owner");
            owner.setEmail(defaultManufacturerEmail);
            owner.setPassword(passwordEncoder.encode("123456"));  //default password
            owner.setRoles(Roles.OWNER);
            owner.setCreatedAt(LocalDateTime.now());
            owner.setUpdatedAt(LocalDateTime.now());
            owner.setActive(true);
            owner.setVerified(true);

            userRepository.save(owner);
            System.out.println("Default Owner is Created : " + defaultManufacturerEmail);

        } else {
            System.out.println("Owner Already exists...");
        }



        //For Central Office :
        if (centralOfficeRepository.count() == 0) {
            CentralOffice office = new CentralOffice();
            office.setCentralOfficeName("Gear Up Office");
            office.setCity("Pune");
            office.setAddress("VadGoan Sheri, Pune, Maharashtra");
            office.setContactEmail("contact@gearup.com");
            office.setContactNumber("+91-9876543210");

            centralOfficeRepository.save(office);
            System.out.println("Central Office created successfully!");
        }
    }
}
