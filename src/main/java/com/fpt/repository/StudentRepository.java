package com.fpt.repository;

import com.fpt.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // 1. Tìm student theo firstName (JPQL)
    @Query("SELECT s FROM Student s WHERE s.firstName = :firstName")
    List<Student> findByFirstName(@Param("firstName") String firstName);

    // 2. Tìm student có tuổi lớn hơn 20 (Native Query)
    @Query(value = "SELECT * FROM student WHERE TIMESTAMPDIFF(YEAR, birthday, CURDATE()) > 20", nativeQuery = true)
    List<Student> findStudentsOlderThan20();

    // 3. Tìm student có tuổi lớn hơn 20 và sống ở một address cụ thể (Native Query)
    @Query(value = "SELECT * FROM student WHERE TIMESTAMPDIFF(YEAR, birthday, CURDATE()) > 20 AND address LIKE CONCAT('%', :address, '%')", nativeQuery = true)
    List<Student> findStudentsOlderThan20AndAddress(@Param("address") String address);

    // 4. Tìm student theo ID
    Student findStudentById(Integer id);
}