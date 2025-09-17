package com.example.studentresultsystem;

public class Topper {
    private Student student;
    private Marks marks;

    public Topper(Student student, Marks marks) {
        this.student = student;
        this.marks = marks;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Marks getMarks() {
        return marks;
    }

    public void setMarks(Marks marks) {
        this.marks = marks;
    }
}