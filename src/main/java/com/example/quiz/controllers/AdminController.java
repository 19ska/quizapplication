package com.example.quiz.controllers;

import com.example.quiz.dto.Admindto;
import com.example.quiz.entities.Admin;
import com.example.quiz.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Admin admin) {
        Admin savedAdmin = adminService.registerAdmin(admin);
        return ResponseEntity.ok("Admin registered successfully with username: " + savedAdmin.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Admindto loginRequest) {
        boolean isAuthenticated = adminService.loginAdmin(loginRequest.getUsername(), loginRequest.getPassword());

        Map<String, String> response = new HashMap<>();
        if (isAuthenticated) {
            response.put("message", "Login successful");
            return ResponseEntity.ok(response); // Return JSON
        } else {
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // Return JSON
        }
    }

}

