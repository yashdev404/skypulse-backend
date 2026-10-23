package com.yash.skypulse_backend.controller;

import com.yash.skypulse_backend.dto.AuthRequest;
import com.yash.skypulse_backend.dto.AuthResponse;
import com.yash.skypulse_backend.dto.RegisterRequest;
import com.yash.skypulse_backend.model.User;
import com.yash.skypulse_backend.repository.UserRepository;
import com.yash.skypulse_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already registered!");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword())); // BCrypt encryption
        newUser.setDateOfBirth(request.getDateOfBirth());
        newUser.setBio(request.getBio() != null ? request.getBio() : "Space Explorer");
        newUser.setSpaceRank("Cadet 🚀");

        User savedUser = userRepository.save(newUser);
        String token = jwtUtil.generateToken(savedUser.getUsername(), savedUser.getId());

        return ResponseEntity.ok(new AuthResponse(token, savedUser.getId(), savedUser.getUsername(), savedUser.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElse(null);
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid username or password!");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getId());
        return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getUsername(), user.getEmail()));
    }

    @PutMapping("/password")
    public ResponseEntity<?> changePassword(@RequestBody com.yash.skypulse_backend.dto.ChangePasswordRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElse(null);
        if (user == null || !passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid username or current password!");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return ResponseEntity.ok("Password updated successfully!");
    }
}