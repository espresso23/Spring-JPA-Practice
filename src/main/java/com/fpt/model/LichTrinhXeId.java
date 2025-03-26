package com.fpt.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class LichTrinhXeId implements Serializable {
    private String xe;       // Tương ứng với maXe
    private LocalDate ngayXuatBen;
    private LocalDateTime gioXuatBen;

    public LichTrinhXeId() {
    }

    public LichTrinhXeId(String xe, LocalDate ngayXuatBen, LocalDateTime gioXuatBen) {
        this.xe = xe;
        this.ngayXuatBen = ngayXuatBen;
        this.gioXuatBen = gioXuatBen;
    }

    // Getters and setters
    public String getXe() {
        return xe;
    }

    public void setXe(String xe) {
        this.xe = xe;
    }

    public LocalDate getNgayXuatBen() {
        return ngayXuatBen;
    }

    public void setNgayXuatBen(LocalDate ngayXuatBen) {
        this.ngayXuatBen = ngayXuatBen;
    }

    public LocalDateTime getGioXuatBen() {
        return gioXuatBen;
    }

    public void setGioXuatBen(LocalDateTime gioXuatBen) {
        this.gioXuatBen = gioXuatBen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LichTrinhXeId that = (LichTrinhXeId) o;
        return Objects.equals(xe, that.xe) &&
                Objects.equals(ngayXuatBen, that.ngayXuatBen) &&
                Objects.equals(gioXuatBen, that.gioXuatBen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xe, ngayXuatBen, gioXuatBen);
    }
}