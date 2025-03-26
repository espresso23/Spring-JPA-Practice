package com.fpt.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TUYENXE")
public class TuyenXe {
    @Id
    @Column(name = "MaTuyen")
    private String maTuyen;

    @Column(name = "TenTuyen")
    private String tenTuyen;

    @Column(name = "DonGia")
    private Double donGia;

    // Constructors, getters and setters
    public TuyenXe() {
    }

    // Getters and setters
    public String getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(String maTuyen) {
        this.maTuyen = maTuyen;
    }

    public String getTenTuyen() {
        return tenTuyen;
    }

    public void setTenTuyen(String tenTuyen) {
        this.tenTuyen = tenTuyen;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

}