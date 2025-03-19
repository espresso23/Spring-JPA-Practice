package com.fpt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentAndCoursePageController {

    @GetMapping("/addStudent")
    public String addStudentPage() {
        return "addStudent"; // This will render addStudent.html from templates folder
    }

    @GetMapping("/addCourse")
    public String addCoursePage() {
        return "addCourse"; // This will render addCourse.html from templates folder
    }

    @GetMapping("/function")
    public String showFunctionPage() {
        return "function"; // This will render function.html from templates folder
    }
}
