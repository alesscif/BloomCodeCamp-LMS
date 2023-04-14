package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.enums.AssignmentStatusEnum;
import com.hcc.enums.AuthorityEnum;
import com.hcc.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepo;
    public List<Assignment> getAssignmentsByUser(User user) {
        if (user.getAuthorities().contains(AuthorityEnum.ROLE_REVIEWER.name()))
            return assignmentRepo.findByStatus(AssignmentStatusEnum.SUBMITTED.name());
         else
            return assignmentRepo.findByUsername(user.getUsername());
    }
    public Assignment getAssignmentById(Long id) {
        return assignmentRepo.getReferenceById(id);
    }

    public Assignment saveAssignment(Integer number, String githubUrl, String branch, User user) {
        return assignmentRepo.save(new Assignment(AssignmentStatusEnum.SUBMITTED.name(), number, githubUrl, branch, null, user, null));
    }

    public Assignment editAssignment(User user, Long id, List<String> fields) {
        if (user.getAuthorities().contains(AuthorityEnum.ROLE_REVIEWER.name())) {
            Assignment assignment = assignmentRepo.getReferenceById(id);
            assignment.setCodeReviewer(user);
            assignment.setStatus(fields.get(0));
            assignment.setReviewVideoUrl(fields.get(1));
            return assignmentRepo.save(assignment);
        }
        else {
            Assignment assignment = assignmentRepo.getReferenceById(id);
            assignment.setStatus(AssignmentStatusEnum.SUBMITTED.name());
            assignment.setGithubUrl(fields.get(0));
            assignment.setBranch(fields.get(1));
            return assignmentRepo.save(assignment);
        }
    }

}
