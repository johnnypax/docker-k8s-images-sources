package dev.archety.studentmanager.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dev.archety.studentmanager.dto.StudentRequest;
import dev.archety.studentmanager.model.StudentStatus;
import dev.archety.studentmanager.service.StudentService;

@Component
public class DataSeeder implements CommandLineRunner {

    private final StudentService studentService;

    public DataSeeder(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) {
        if (studentService.count() > 0) {
            return;
        }

        seed("John", "Carter", "john.carter@example.com", "Computer Science", 21, StudentStatus.ACTIVE);
        seed("Emma", "Brown", "emma.brown@example.com", "Mathematics", 22, StudentStatus.ACTIVE);
        seed("Liam", "Smith", "liam.smith@example.com", "Physics", 24, StudentStatus.SUSPENDED);
    }

    private void seed(String firstName, String lastName, String email, String course, int age, StudentStatus status) {
        StudentRequest request = new StudentRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setEmail(email);
        request.setCourse(course);
        request.setAge(age);
        request.setStatus(status);
        studentService.create(request);
    }
}