package com.example.studentresultsystem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarksRepository extends JpaRepository<Marks, Long> {
    Marks findByStudentId(Long studentId);
}