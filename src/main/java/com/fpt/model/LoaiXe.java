package com.fpt.model;

import jakarta.persistence.*;

@Entity
@Table(name = "LOAIXE")
public class LoaiXe {
    @Id
    @Column(name = "MaloaiXe")
    private String maLoaiXe;

    @Column(name = "MoTaloaiXe" )
    private String moTaLoaiXe;

    @Column(name = "SoluongChoNgoi")
    private Integer soLuongChoNgoi;

    // Constructors, getters and setters
    public LoaiXe() {
    }

    // Getters and setters
    public String getMaLoaiXe() {
        return maLoaiXe;
    }

    public void setMaLoaiXe(String maLoaiXe) {
        this.maLoaiXe = maLoaiXe;
    }

    public String getMoTaLoaiXe() {
        return moTaLoaiXe;
    }

    public void setMoTaLoaiXe(String moTaLoaiXe) {
        this.moTaLoaiXe = moTaLoaiXe;
    }

    public Integer getSoLuongChoNgoi() {
        return soLuongChoNgoi;
    }

    public void setSoLuongChoNgoi(Integer soLuongChoNgoi) {
        this.soLuongChoNgoi = soLuongChoNgoi;
    }
}