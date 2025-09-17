package com.example.studentresultsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MarksRepository marksRepository;

    @PostMapping("/students/register")
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {
        if (studentRepository.findByUsername(student.getUsername()) != null) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.CREATED);
    }

    @PostMapping("/students/login")
    public ResponseEntity<?> loginStudent(@RequestBody Student student) {
        Student existingStudent = studentRepository.findByUsername(student.getUsername());
        if (existingStudent == null || !existingStudent.getPassword().equals(student.getPassword())) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
        Map<String, Long> response = new HashMap<>();
        response.put("studentId", existingStudent.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/marks")
    public ResponseEntity<?> enterMarks(@RequestBody Marks marks) {
        return new ResponseEntity<>(marksRepository.save(marks), HttpStatus.CREATED);
    }

    @GetMapping("/students/{studentId}/average")
    public ResponseEntity<?> getStudentAverage(@PathVariable Long studentId) {
        List<Marks> marks = marksRepository.findByStudentId(studentId);
        if (marks.isEmpty()) {
            return new ResponseEntity<>("No marks found for this student", HttpStatus.NOT_FOUND);
        }
        double sum = 0;
        for (Marks mark : marks) {
            sum += mark.getScore();
        }
        double average = sum / marks.size();
        Map<String, Double> response = new HashMap<>();
        response.put("average", average);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/students/topper")
    public ResponseEntity<?> getTopper() {
        List<Topper> toppers = marksRepository.findTopper();
        if (toppers.isEmpty()) {
            return new ResponseEntity<>("No marks have been entered yet", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(toppers.get(0), HttpStatus.OK);
    }
}
