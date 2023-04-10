package com.hcc.controllers;

import com.hcc.DTOs.AuthCredentialRequest;
import com.hcc.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/login")
    public void login(@RequestBody AuthCredentialRequest authRequest) {
    }
    @PostMapping("/validate")
    public void validateToken(@RequestBody String token, UserDetails user) {
         if (!jwtUtil.validateToken(token, user))
    }
}

