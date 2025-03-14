package com.fpt.service;

import com.fpt.dto.request.CourseCreationRequest;
import com.fpt.dto.request.StudentCreationRequest;
import com.fpt.model.Course;
import com.fpt.model.Student;
import com.fpt.repository.CourseRepository;
import com.fpt.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentAndCourseService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Student> findStudentsByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    @Transactional
    public Student createStudent(StudentCreationRequest request) {
        Student student = new Student();
        student.setLastName(request.getLastName());
        student.setFirstName(request.getFirstName());
        student.setPhone(request.getPhone());
        student.setBirthday(request.getBirthday());
        student.setAddress(request.getAddress());
        return studentRepository.save(student);
    }

    @Transactional
    public Course createCourse(CourseCreationRequest request) {
        Student student = studentRepository.findById(request.getStudentID())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + request.getStudentID()));
        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setStartDate(request.getStartDate());
        course.setEndDate(request.getEndDate());
        course.setStudent(student);
        return courseRepository.save(course);
    }

    public List<Student> findStudentsOlderThan20() {
        return studentRepository.findStudentsOlderThan20();
    }

    public List<Student> findStudentsOlderThan20AndAddress(String address) {
        return studentRepository.findStudentsOlderThan20AndAddress(address);
    }

    public List<Course> findCoursesByDuration(LocalDate startDate, LocalDate endDate) {
        return courseRepository.findCoursesByDuration(startDate, endDate);
    }

    public List<Course> findCoursesByStudentId(Integer studentId) {
        return courseRepository.findCoursesByStudentId(studentId);
    }
}