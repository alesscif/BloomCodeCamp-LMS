package com.hcc.DTOs;

import com.hcc.entities.Assignment;
import com.hcc.enums.AssignmentEnum;
import com.hcc.enums.AssignmentStatusEnum;

import java.util.List;

public class AssignmentResponseDTO {
    Assignment assignment;
    List<AssignmentEnum> AssignmentEnums;
    List<AssignmentStatusEnum> StatusEnums;

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public List<AssignmentEnum> getAssignmentEnums() {
        return AssignmentEnums;
    }

    public void setAssignmentEnums(List<AssignmentEnum> assignmentEnums) {
        AssignmentEnums = assignmentEnums;
    }

    public List<AssignmentStatusEnum> getStatusEnums() {
        return StatusEnums;
    }

    public void setStatusEnums(List<AssignmentStatusEnum> statusEnums) {
        StatusEnums = statusEnums;
    }
}
