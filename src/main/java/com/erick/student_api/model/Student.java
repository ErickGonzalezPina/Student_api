package com.erick.student_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentID;
    private String name;
    private String semester;
    private String course;

    // Constructors
    public Student() {}

    public Student(String name, String semester, String course) {
        this.name = name;
        this.semester = semester;
        this.course = course;
    }

    // Getters and Setters
    public long getStudentID() {
        return studentID;
    }
    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
}
