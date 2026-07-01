package com.erick.student_api.dto;

public class StudentRequest {
    // Fields
    private String name;
    private String semester;
    private String course;

    // Constructors
    public StudentRequest() {}

    public StudentRequest(String name, String semester, String course) {
        this.name = name;
        this.semester = semester;
        this.course = course;
    }

    // Getters and Setters
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
