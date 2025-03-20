package com.fpt.dto.request;


import java.time.LocalDate;

public class CourseCreationRequest {

    private String courseName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer studentID;

    public String getCourseName() {
        return courseName;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

}
