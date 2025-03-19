package com.fpt.controller;

import com.fpt.dto.request.CourseCreationRequest;
import com.fpt.dto.request.StudentCreationRequest;
import com.fpt.model.Course;
import com.fpt.model.Student;
import com.fpt.service.StudentAndCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentAndCourseController {

    @Autowired
    private StudentAndCourseService studentAndCourseService;

    @PostMapping("/students-json")
    public Student createStudent(@RequestBody StudentCreationRequest request) {
        System.out.println("Received Request: " + request);
        return studentAndCourseService.createStudent(request);
    }
    @PostMapping("/students-urlencoded")
    public Student createStudent(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String address,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthday,
            @RequestParam String phone) {

        StudentCreationRequest request = new StudentCreationRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setAddress(address);
        request.setBirthday(birthday);
        request.setPhone(phone);

        return studentAndCourseService.createStudent(request);
    }
    @PostMapping("/courses")
    public Course createCourse(@RequestBody CourseCreationRequest request) {//@ModelAttribute có tể sử dụng thay cho @RequestBody
        return studentAndCourseService.createCourse(request);
    }

    // Tìm student theo firstName
    @GetMapping("/students/firstName/{firstName}")
    public List<Student> findStudentsByFirstName(@PathVariable String firstName) {
        return studentAndCourseService.findStudentsByFirstName(firstName);
    }

    // Tìm student có tuổi lớn hơn 20
    @GetMapping("/students/older-than-20")
    public List<Student> findStudentsOlderThan20() {
        return studentAndCourseService.findStudentsOlderThan20();
    }

    // Tìm student có tuổi lớn hơn 20 và địa chỉ cụ thể
    @GetMapping("/students/older-than-20-and-address/{address}")
    public List<Student> findStudentsOlderThan20AndAddress(@PathVariable String address) {
        return studentAndCourseService.findStudentsOlderThan20AndAddress(address);
    }

    // Tìm course theo khoảng thời gian
    @GetMapping("/courses/duration")
    public List<Course> findCoursesByDuration(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return studentAndCourseService.findCoursesByDuration(startDate, endDate);
    }

    // Tìm course theo studentId
    @GetMapping("/courses/student/{studentId}")
    public List<Course> findCoursesByStudentId(@PathVariable Integer studentId) {
        return studentAndCourseService.findCoursesByStudentId(studentId);
    }

    // Update student
    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable Integer studentId, @RequestBody StudentCreationRequest request) {
        return studentAndCourseService.updateStudent(studentId, request);
    }

    // Delete student
    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable Integer studentId) {
        studentAndCourseService.deleteStudent(studentId);
    }

    // Update course
    @PutMapping("/courses/{courseId}")
    public Course updateCourse(@PathVariable Integer courseId, @RequestBody CourseCreationRequest request) {
        return studentAndCourseService.updateCourse(courseId, request);
    }

    // Delete course
    @DeleteMapping("/courses/{courseId}")
    public void deleteCourse(@PathVariable Integer courseId) {
        studentAndCourseService.deleteCourse(courseId);
    }

}