package com.fpt.repository;

import com.fpt.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
public interface CourseRepository extends JpaRepository<Course, Integer> {
    // 4. Tìm course theo khoảng thời gian (duration)
    @Query("SELECT c FROM Course c WHERE c.startDate >= :startDate AND c.endDate <= :endDate")
    List<Course> findCoursesByDuration(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // 5. Tìm tất cả course của một student cụ thể (theo studentID)
    @Query("SELECT c FROM Course c WHERE c.student.id = :studentId")
    List<Course> findCoursesByStudentId(@Param("studentId") int studentId);
}