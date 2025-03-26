package com.fpt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@Entity
@Table(name = "XE")
public class Xe {
    @Id
    @Column(name = "ma_xe") // Sửa thành tên cột chính xác trong database
    private String maXe;

    @Column(name = "HangSanXuat")
    private String hangSanXuat;

    @ManyToOne
    @JoinColumn(name = "MaloaiXe")
    private LoaiXe loaiXe;

    @Column(name = "BienSo")
    @Pattern(regexp = "\\d{2}[A-Z]\\d-\\d{5}", message = "Biển số phải có dạng: xxYx-xxxxx")
    private String bienSo;

    @Column(name = "HanKiemDinh")
    private LocalDate hanKiemDinh;

    @ManyToOne
    @JoinColumn(name = "MaNhaXe")
    private NhaXe nhaXe;

    // Constructors, getters and setters
    public Xe() {
    }

    // Getters and setters
    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public String getHangSanXuat() {
        return hangSanXuat;
    }

    public void setHangSanXuat(String hangSanXuat) {
        this.hangSanXuat = hangSanXuat;
    }

    public LoaiXe getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(LoaiXe loaiXe) {
        this.loaiXe = loaiXe;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    public NhaXe getNhaXe() {
        return nhaXe;
    }

    public void setNhaXe(NhaXe nhaXe) {
        this.nhaXe = nhaXe;
    }

    public LocalDate getHanKiemDinh() {
        return hanKiemDinh;
    }

    public void setHanKiemDinh(LocalDate hanKiemDinh) {
        this.hanKiemDinh = hanKiemDinh;
    }

    @Override
    public String toString() {
        return "Xe{" +
                "bienSo='" + bienSo + '\'' +
                ", maXe='" + maXe + '\'' +
                ", hangSanXuat='" + hangSanXuat + '\'' +
                ", loaiXe=" + loaiXe +
                ", hanKiemDinh=" + hanKiemDinh +
                ", nhaXe=" + nhaXe +
                '}';
    }
}
