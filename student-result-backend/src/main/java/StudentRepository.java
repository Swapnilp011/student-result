package com.example.studentresult.repository;

import com.example.studentresult.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByUsername(String username);
}