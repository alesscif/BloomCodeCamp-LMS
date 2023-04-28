package com.hcc.controllers;

import com.hcc.entities.User;
import com.hcc.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.hcc.entities.Assignment;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    @Autowired
    AssignmentService assignmentService;
    @GetMapping
    public ResponseEntity<List<Assignment>> getAssignmentsByUser(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(assignmentService.getAssignmentsByUser(user), HttpStatus.OK);
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
    public ResponseEntity<Assignment> putAssignmentById(@AuthenticationPrincipal User user, @PathVariable Long id, @RequestBody List<String> fields) {
        return new ResponseEntity<>(assignmentService.editAssignment(user, id, fields), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Assignment> postAssignment(@AuthenticationPrincipal User user, @RequestBody List<String> fields) {
        return new ResponseEntity<>(assignmentService.saveAssignment(Integer.valueOf(fields.get(0)), fields.get(1), fields.get(2), user), HttpStatus.OK);
    }
}
