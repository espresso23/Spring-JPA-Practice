package com.fpt.repository;

import com.fpt.model.LichTrinhXe;
import com.fpt.model.LichTrinhXeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface LichTrinhXeRepository extends JpaRepository<LichTrinhXe, LichTrinhXeId> {

    // Tìm lịch trình theo mã xe
    List<LichTrinhXe> findByXe_MaXe(String maXe);

    // Tìm lịch trình theo ngày xuất bến
    List<LichTrinhXe> findByNgayXuatBen(LocalDate ngay);

    // Tìm lịch trình trong khoảng thời gian
    @Query("SELECT l FROM LichTrinhXe l WHERE l.ngayXuatBen BETWEEN :startDate AND :endDate")
    List<LichTrinhXe> findLichTrinhTrongKhoangThoiGian(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    // Tìm lịch trình theo tuyến xe
    List<LichTrinhXe> findByTuyenXe_MaTuyen(String maTuyen);

    // Custom query để lấy thông tin tổng hợp
    @Query("SELECT l FROM LichTrinhXe l JOIN FETCH l.xe JOIN FETCH l.tuyenXe WHERE l.xe.maXe = :maXe")
    List<LichTrinhXe> findLichTrinhWithXeAndTuyen(@Param("maXe") String maXe);
    @Query("select l from LichTrinhXe l where l.xe.nhaXe.tenNhaXe LIKE CONCAT('%', :tenNhaXe, '%')")
    List<LichTrinhXe> findLichTrinhXeByNhaXe(@Param("tenNhaXe") String tenNhaXe);

}