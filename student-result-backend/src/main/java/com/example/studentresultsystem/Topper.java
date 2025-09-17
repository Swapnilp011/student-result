package com.example.studentresultsystem;

public class Topper {
    private String username;
    private double average;

    public Topper(String username, double average) {
        this.username = username;
        this.average = average;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}