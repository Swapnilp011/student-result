package com.example.studentresultsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MarksRepository marksRepository;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PostMapping("/{id}/marks")
    public Marks addMarks(@PathVariable Long id, @RequestBody Marks marks) {
        marks.setStudentId(id);
        int total = marks.getSubject1() + marks.getSubject2() + marks.getSubject3();
        marks.setTotal(total);
        marks.setPercentage(total / 3.0);
        return marksRepository.save(marks);
    }

    @GetMapping("/{id}/marks")
    public Marks getMarks(@PathVariable Long id) {
        return marksRepository.findByStudentId(id);
    }

    @GetMapping("/topper")
    public Topper getTopper() {
        List<Marks> allMarks = marksRepository.findAll();
        if (allMarks.isEmpty()) {
            return null;
        }
        Marks topperMarks = allMarks.stream().max((m1, m2) -> Double.compare(m1.getPercentage(), m2.getPercentage())).get();
        Student student = studentRepository.findById(topperMarks.getStudentId()).get();
        return new Topper(student, topperMarks);
    }
}
