package com.example.quiz.services;

import com.example.quiz.entities.Admin;
import com.example.quiz.repositories.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AdminService {


    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AdminRepository adminRepository;


    @Transactional
    public Admin registerAdmin(Admin admin) {
        try {
            Admin savedAdmin = adminRepository.save(admin); // Save and return the saved entity
            logger.info("Admin registered successfully: {}", savedAdmin.getUsername());
            return savedAdmin;
        } catch (Exception e) {
            logger.error("Error during registration", e);
            throw e;  // Rethrow to ensure the transaction is rolled back if needed
        }
    }

    //public boolean loginAdmin(String username, String password) {
       // Optional<Admin> admin = adminRepository.findByUsername(username);
       // return admin.isPresent() && admin.get().getPassword().equals(password);
    //}
    public boolean loginAdmin(String username, String password) {
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);

        if (optionalAdmin.isEmpty()) { // Correct way to check if admin exists
            System.out.println("Admin not found with username: " + username);
            return false;
        }

        Admin admin = optionalAdmin.get(); // Extract Admin object safely

        if (!admin.getPassword().equals(password)) {
            System.out.println("Password mismatch for username: " + username);
            return false;
        }

        System.out.println("Login successful for username: " + username);
        return true;
    }
}
