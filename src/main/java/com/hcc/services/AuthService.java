package com.hcc.services;

import com.hcc.DTOs.AuthCredentialRequest;
import com.hcc.entities.User;
import com.hcc.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserDetailsService userDetailService;
    public String login(AuthCredentialRequest request) {
        User user = (User) userDetailService.loadUserByUsername(request.getUsername());
        if (user.getPassword().equals(request.getPassword())) {
            user.setPassword(null);
            return jwtUtil.generateToken(user);
        }
        else throw new IllegalArgumentException("invalid credentials");
    }
    public boolean validate(String token) {
        User user = new User();
        user.setUsername(jwtUtil.getUsernameFromToken(token));
        return jwtUtil.validateToken(token, user);
    }
}
