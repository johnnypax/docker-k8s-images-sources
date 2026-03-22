package dev.archety.studentmanager.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.archety.studentmanager.dto.StudentRequest;
import dev.archety.studentmanager.dto.StudentResponse;
import dev.archety.studentmanager.dto.StudentStatusUpdateRequest;
import dev.archety.studentmanager.exception.StudentNotFoundException;
import dev.archety.studentmanager.model.Student;
import dev.archety.studentmanager.model.StudentStatus;
import dev.archety.studentmanager.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<StudentResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public StudentResponse findById(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " not found"));
        return toResponse(student);
    }

    public StudentResponse create(StudentRequest request) {
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setCourse(request.getCourse());
        student.setAge(request.getAge());
        student.setStatus(request.getStatus());
        student.setCreatedAt(LocalDateTime.now());

        return toResponse(repository.save(student));
    }

    public StudentResponse update(Long id, StudentRequest request) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " not found"));

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setCourse(request.getCourse());
        student.setAge(request.getAge());
        student.setStatus(request.getStatus());

        return toResponse(repository.save(student));
    }

    public StudentResponse updateStatus(Long id, StudentStatusUpdateRequest request) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " not found"));

        student.setStatus(request.getStatus());
        return toResponse(repository.save(student));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }
        repository.deleteById(id);
    }

    public List<StudentResponse> search(String course, StudentStatus status) {
        List<Student> students;

        if (course != null && status != null) {
            students = repository.findByCourseIgnoreCaseAndStatus(course, status);
        } else if (course != null) {
            students = repository.findByCourseIgnoreCase(course);
        } else if (status != null) {
            students = repository.findByStatus(status);
        } else {
            students = repository.findAll();
        }

        return students.stream().map(this::toResponse).toList();
    }

    public long count() {
        return repository.count();
    }

    private StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getCourse(),
                student.getAge(),
                student.getStatus(),
                student.getCreatedAt()
        );
    }
}