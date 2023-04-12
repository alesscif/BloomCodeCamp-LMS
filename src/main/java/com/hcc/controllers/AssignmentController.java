package com.hcc.controllers;

import com.hcc.enums.AuthorityEnum;
import com.hcc.repositories.AssignmentRepository;
import com.hcc.repositories.AuthorityRepository;
import com.hcc.services.AssignmentService;
import com.hcc.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hcc.entities.Assignment;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/assignments")
public class AssignmentController {
    @Autowired
    AssignmentService assignmentService;
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping
    public ResponseEntity<List<Assignment>> getAssignmentsByUser(@RequestBody String token) {
        String username = jwtUtil.getUsernameFromToken(token);
        return new ResponseEntity<>(assignmentService.loadAssignmentsByUsername(username), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(assignmentService.getAssignmentById(id), HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public void putAssignmentById(@PathVariable Long id) {
    }
    @PostMapping
    public void postAssignment() {
    }
}
