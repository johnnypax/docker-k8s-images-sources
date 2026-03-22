package dev.archety.studentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.archety.studentmanager.model.Student;
import dev.archety.studentmanager.model.StudentStatus;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByCourseIgnoreCase(String course);

    List<Student> findByStatus(StudentStatus status);

    List<Student> findByCourseIgnoreCaseAndStatus(String course, StudentStatus status);
}