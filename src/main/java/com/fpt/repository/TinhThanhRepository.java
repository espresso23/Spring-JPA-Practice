package com.fpt.repository;

import com.fpt.model.TinhThanh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TinhThanhRepository extends JpaRepository<TinhThanh, String> {
}
