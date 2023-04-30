package com.hcc.controllers;

import com.hcc.DTOs.AuthCredentialRequest;
import com.hcc.entities.User;
import com.hcc.services.AuthService;
import com.hcc.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthCredentialRequest credentialRequest) {
        try {
            Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(credentialRequest.getUsername(), credentialRequest.getPassword()));
            User user = (User) auth.getPrincipal();
            user.setPassword(null);
            return ResponseEntity.ok().body(jwtUtil.generateToken(user));
        }
        catch (BadCredentialsException e) {
            return new ResponseEntity<>("invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestBody String token) {
        return authService.validate(token) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}

