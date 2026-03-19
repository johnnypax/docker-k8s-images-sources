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
        seed("John", "Carter", "john.carter@example.com", "Computer Science", 21, StudentStatus.ACTIVE);
        seed("Emma", "Brown", "emma.brown@example.com", "Mathematics", 22, StudentStatus.ACTIVE);
        seed("Liam", "Smith", "liam.smith@example.com", "Physics", 24, StudentStatus.SUSPENDED);
        seed("Olivia", "Johnson", "olivia.johnson@example.com", "Engineering", 23, StudentStatus.GRADUATED);
        seed("Noah", "Davis", "noah.davis@example.com", "Computer Science", 20, StudentStatus.ACTIVE);
        seed("Sophia", "Miller", "sophia.miller@example.com", "Biology", 25, StudentStatus.ACTIVE);
        seed("James", "Wilson", "james.wilson@example.com", "Chemistry", 26, StudentStatus.SUSPENDED);
        seed("Ava", "Moore", "ava.moore@example.com", "Literature", 22, StudentStatus.GRADUATED);
        seed("William", "Taylor", "william.taylor@example.com", "Computer Science", 27, StudentStatus.ACTIVE);
        seed("Mia", "Anderson", "mia.anderson@example.com", "Engineering", 24, StudentStatus.ACTIVE);
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
