package com.fpt.model;
import jakarta.persistence.*;
@Entity
@Table(name = "NHAXE")
public class NhaXe {
    @Id
    @Column(name = "MaNhaXe", length = 10)
    private String maNhaXe;

    @Column(name = "TenNhaXe", length = 100)
    private String tenNhaXe;

    @Column(name = "NamThanhlap")
    private Integer namThanhLap;

    // Constructors, getters and setters
    public NhaXe() {
    }

    // Getters and setters
    public String getMaNhaXe() {
        return maNhaXe;
    }

    public void setMaNhaXe(String maNhaXe) {
        this.maNhaXe = maNhaXe;
    }

    public String getTenNhaXe() {
        return tenNhaXe;
    }

    public void setTenNhaXe(String tenNhaXe) {
        this.tenNhaXe = tenNhaXe;
    }

    public Integer getNamThanhLap() {
        return namThanhLap;
    }

    public void setNamThanhLap(Integer namThanhLap) {
        this.namThanhLap = namThanhLap;
    }

}