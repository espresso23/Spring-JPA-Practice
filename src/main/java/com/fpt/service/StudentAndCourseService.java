package com.fpt.service;

import com.fpt.dto.request.CourseCreationRequest;
import com.fpt.dto.request.StudentCreationRequest;
import com.fpt.model.Course;
import com.fpt.model.Student;
import com.fpt.repository.CourseRepository;
import com.fpt.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentAndCourseService {

    private StudentRepository studentRepository;

    private CourseRepository courseRepository;

    @Autowired
    public StudentAndCourseService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

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
        if (request.getStudentID() == null) {
            throw new IllegalArgumentException("Student ID must not be null");
        }

        Student student = studentRepository.findById(request.getStudentID())
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + request.getStudentID()));

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

    @Transactional
    public Student updateStudent(Integer studentId, StudentCreationRequest request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));
        
        student.setLastName(request.getLastName());
        student.setFirstName(request.getFirstName());
        student.setPhone(request.getPhone());
        student.setBirthday(request.getBirthday());
        student.setAddress(request.getAddress());
        
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));
        
        // Xóa tất cả các course liên quan đến student
        List<Course> courses = courseRepository.findCoursesByStudentId(studentId);
        courseRepository.deleteAll(courses);
        
        // Sau đó xóa student
        studentRepository.delete(student);
    }

    @Transactional
    public Course updateCourse(Integer courseId, CourseCreationRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));
        
        course.setCourseName(request.getCourseName());
        course.setStartDate(request.getStartDate());
        course.setEndDate(request.getEndDate());
        
        return courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(Integer courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));
        courseRepository.delete(course);
    }

}
