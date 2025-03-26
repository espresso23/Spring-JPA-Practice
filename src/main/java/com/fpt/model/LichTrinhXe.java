package com.fpt.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "LICHTRINHXE")
@IdClass(LichTrinhXeId.class)
public class LichTrinhXe {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_xe", foreignKey = @ForeignKey(name = "FK_LICHTRINHXE_XE"))
    private Xe xe;

    @Id
    @Column(name = "NgayXuatBen")
    @Temporal(TemporalType.DATE)
    private LocalDate ngayXuatBen;

    @Id
    @Column(name = "GioXuatBen", columnDefinition = "DATETIME")
    private LocalDateTime gioXuatBen;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTuyen", foreignKey = @ForeignKey(name = "FK_LICHTRINHXE_TUYENXE"))
    private TuyenXe tuyenXe;

    @Column(name = "TenTaiXe", length = 100)
    private String tenTaiXe;

    @Column(name = "SoluongHanhKhach")
    private Integer soLuongHanhKhach;

    @Transient
    private String ngayXuatBenForm; // Nhận giá trị từ input date

    @Transient
    private String gioXuatBenForm;  // Nhận giá trị từ input time

    public String getGioXuatBenForm() {
        return gioXuatBenForm;
    }

    public void setGioXuatBenForm(String gioXuatBenForm) {
        this.gioXuatBenForm = gioXuatBenForm;
    }

    public String getNgayXuatBenForm() {
        return ngayXuatBenForm;
    }

    public void setNgayXuatBenForm(String ngayXuatBenForm) {
        this.ngayXuatBenForm = ngayXuatBenForm;
    }

    // Constructors
    public LichTrinhXe() {
    }

    public LichTrinhXe(Xe xe, LocalDate ngayXuatBen, LocalDateTime gioXuatBen) {
        this.xe = xe;
        this.ngayXuatBen = ngayXuatBen;
        this.gioXuatBen = gioXuatBen;
    }

    // Getters and setters
    public Xe getXe() {
        return xe;
    }

    public void setXe(Xe xe) {
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

    public TuyenXe getTuyenXe() {
        return tuyenXe;
    }

    public void setTuyenXe(TuyenXe tuyenXe) {
        this.tuyenXe = tuyenXe;
    }

    public String getTenTaiXe() {
        return tenTaiXe;
    }

    public void setTenTaiXe(String tenTaiXe) {
        this.tenTaiXe = tenTaiXe;
    }

    public Integer getSoLuongHanhKhach() {
        return soLuongHanhKhach;
    }

    public void setSoLuongHanhKhach(Integer soLuongHanhKhach) {
        this.soLuongHanhKhach = soLuongHanhKhach;
    }

}