package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.enums.AuthorityEnum;
import com.hcc.repositories.AssignmentRepository;
import com.hcc.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {
    @Autowired
    AuthorityRepository authRepo;
    @Autowired
    AssignmentRepository assignmentRepo;
    public List<Assignment> loadAssignmentsByUsername(String username) {
        if (authRepo.findByUsername(username).contains(AuthorityEnum.ROLE_REVIEWER))
            return assignmentRepo.findAll();
        else
            return assignmentRepo.findByUsername(username);
    }
    public Assignment getAssignmentById(Long id) {
        return assignmentRepo.getReferenceById(id);
    }
}
