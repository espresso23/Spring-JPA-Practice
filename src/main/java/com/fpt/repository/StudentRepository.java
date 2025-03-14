package com.fpt.repository;

import com.fpt.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // 1. Tìm student theo firstName (JPQL)
    @Query("SELECT s FROM Student s WHERE s.firstName = :firstName")
    List<Student> findByFirstName(@Param("firstName") String firstName);

    // 2. Tìm student có tuổi lớn hơn 20 (Native Query)
    @Query(value = "SELECT * FROM Student WHERE DATEDIFF(YEAR, birthday, GETDATE()) > 20", nativeQuery = true)
    List<Student> findStudentsOlderThan20();

    // 3. Tìm student có tuổi lớn hơn 20 và sống ở một address cụ thể (Native Query)
    @Query(value = "SELECT * FROM Student WHERE DATEDIFF(YEAR, birthday, GETDATE()) > 20 AND address LIKE '%' + :address + '%'", nativeQuery = true)
    List<Student> findStudentsOlderThan20AndAddress(@Param("address") String address);
}