package dev.archety.studentmanager.dto;

import dev.archety.studentmanager.model.StudentStatus;
import jakarta.validation.constraints.NotNull;

public class StudentStatusUpdateRequest {

    @NotNull(message = "Status is required")
    private StudentStatus status;

    public StudentStatusUpdateRequest() {
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }
}
