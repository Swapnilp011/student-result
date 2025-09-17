package com.example.studentresult.controller;

import com.example.studentresult.model.Student;
import com.example.studentresult.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Allow React dev server
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Student student) {
        try {
            studentService.register(student);
            return ResponseEntity.ok(Map.of("message", "Registration successful!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username already exists."));
        }
    }
}