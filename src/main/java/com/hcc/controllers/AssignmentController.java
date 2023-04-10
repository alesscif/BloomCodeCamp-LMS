package com.hcc.controllers;

import com.hcc.repositories.AssignmentRepository;
import com.hcc.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hcc.entities.Assignment;
import java.util.List;

@RestController
@RequestMapping(value = "/api/assignments")
public class AssignmentController {
    @Autowired
    private AssignmentRepository assignmentRepo;
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping
    public List<Assignment> getAssignmentsByUser(@RequestBody String token) {
        String username = jwtUtil.getUsernameFromToken(token);
        return assignmentRepo.findByUsername(username);
    }
    @GetMapping("/{id}")
    public Assignment getAssignmentById(@PathVariable Long id) {
        return assignmentRepo.getReferenceById(id);
    }
    @PutMapping("/{id}")
    public void putAssignmentById(@PathVariable Long id) {
    }
    @PostMapping
    public void postAssignment() {
    }
}
