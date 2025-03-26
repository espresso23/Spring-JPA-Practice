package com.fpt.repository;

import com.fpt.model.LoaiXe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiXeRepository extends JpaRepository<LoaiXe, String> {
}
