package com.studentregistration.model;

public class Student {
    private String regNo, name, email, course, dob, gender;

    public Student(String regNo, String name, String email, String course, String dob, String gender) {
        this.regNo = regNo; this.name = name; this.email = email;
        this.course = course; this.dob = dob; this.gender = gender;
    }
    public String getRegNo() { return regNo; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }
    public String getDob() { return dob; }
    public String getGender() { return gender; }
}
