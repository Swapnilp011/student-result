package com.example.studentresult.service;

import com.example.studentresult.model.Student;
import com.example.studentresult.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void register(Student student) throws Exception {
        if (studentRepository.existsByUsername(student.getUsername())) {
            throw new Exception("Username already exists");
        }
        studentRepository.save(student);
    }
}