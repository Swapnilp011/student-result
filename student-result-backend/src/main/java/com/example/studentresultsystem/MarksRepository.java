package com.example.studentresultsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarksRepository extends JpaRepository<Marks, Long> {
    List<Marks> findByStudentId(Long studentId);

    @Query("SELECT new com.example.studentresultsystem.Topper(s.username, AVG(m.score)) FROM Marks m JOIN Student s ON m.studentId = s.id GROUP BY s.username ORDER BY AVG(m.score) DESC")
    List<Topper> findTopper();
}