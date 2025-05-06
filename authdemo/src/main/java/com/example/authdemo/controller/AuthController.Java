package com.example.authdemo.controller;


import jakarta.annotation.PostConstruct;
import lombok.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.web.bind.annotation.*;

import com.example.authdemo.repository.UserRepositorio;
import com.example.authdemo.jwt.JwtUtil;
import com.example.authdemo.model.User;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepositorio userRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/Login.jsx")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        UserDetails user = new org.springframework.security.core.userdetails.User(
                authRequest.getUsername(), "",
                java.util.Collections.singleton(() -> "USER"));

        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostConstruct
    public void createDefaultUser() {
        if (userRepositorio.findByUsername("admin").isEmpty()) {
            userRepositorio.save(User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .role("ROLE_USER")
                    .build());
        }
    }

    private static class AuthRequest {

        public AuthRequest() {
        }

        private String getUsername() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        private Object getPassword() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}


@AllArgsConstructor
@Data
class AuthResponse {
    private String jwt;
}