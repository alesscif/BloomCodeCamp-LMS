package com.hcc.controllers;

import com.hcc.DTOs.AuthCredentialRequest;
import com.hcc.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody List<String> credentials) {
        AuthCredentialRequest credentialRequest = new AuthCredentialRequest();
        credentialRequest.setUsername(credentials.get(0));
        credentialRequest.setPassword(credentials.get(1));
        try {
            return new ResponseEntity<>(authService.login(credentialRequest), HttpStatus.OK);
        }
        catch (UsernameNotFoundException | IllegalArgumentException e) {
            return new ResponseEntity<>("invalid credentials", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestBody String token) {
        return authService.validate(token) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}

