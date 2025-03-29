package com.fpt.service;

import com.fpt.model.BenhNhan;
import com.fpt.model.DonViDieuTri;
import com.fpt.model.TinhThanh;
import com.fpt.repository.BenhNhanRepository;
import com.fpt.repository.DonViDieuTriRepository;
import com.fpt.repository.TinhThanhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BenhNhanService {

    private final DonViDieuTriService donViDieuTriService;
    private TinhThanhRepository tinhThanhRepository;
    private DonViDieuTriRepository donViDieuTriRepository;
    private BenhNhanRepository benhNhanRepository;

    @Autowired
    public BenhNhanService(BenhNhanRepository benhNhanRepository, DonViDieuTriRepository donViDieuTriRepository, TinhThanhRepository tinhThanhRepository, DonViDieuTriService donViDieuTriService) {
        this.benhNhanRepository = benhNhanRepository;
        this.donViDieuTriRepository = donViDieuTriRepository;
        this.tinhThanhRepository = tinhThanhRepository;
        this.donViDieuTriService = donViDieuTriService;
    }

    public BenhNhan createBenhNhan(BenhNhan benhNhan) {
        DonViDieuTri donViDieuTri = donViDieuTriRepository.findById(benhNhan.getDonViDieuTri().getMaDonVi()).orElseThrow(() -> new RuntimeException("Ma don vi khong ton tai trong he thong"));
        TinhThanh tinhThanh = tinhThanhRepository.findById(benhNhan.getTinhThanh().getMaTinhThanh()).orElseThrow(() -> new RuntimeException("Ma tinh thanh khong ton tai trong he thong"));

        return benhNhanRepository.save(benhNhan);
    }

    public List<BenhNhan> getAllBenhNhan() {
        return benhNhanRepository.findAll();
    }

    public List<BenhNhan> getBenhNhansBySoCMND(String soCMND) {
        System.out.println(soCMND);
        return benhNhanRepository.getBenhNhansBySoCMND(soCMND);
    }
    public BenhNhan getBenhNhansBySoCMND1(String soCMND) {
        System.out.println(soCMND);
        return benhNhanRepository.getBenhNhansBySoCMND1(soCMND);
    }

    public BenhNhan getBenhNhanById(String id) {
        return benhNhanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh nhân với ID: " + id));
    }

    public BenhNhan updateBenhNhan(String id, BenhNhan benhNhan) {
        BenhNhan existing = benhNhanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh nhân với ID: " + id));
        String maDonViCuaBenhNhan = benhNhan.getDonViDieuTri().getMaDonVi();
        String maTinhCuaDonVi = donViDieuTriRepository.getMaTinhByMaDonVi(maDonViCuaBenhNhan);
        if (maTinhCuaDonVi.equals(existing.getTinhThanh().getMaTinhThanh())) {
            existing.setNgayCachLy(benhNhan.getNgayCachLy());
            existing.setDonViDieuTri(benhNhan.getDonViDieuTri());
            return benhNhanRepository.save(existing);
        } else {
            throw new RuntimeException("Don vi dieu tri khong thuoc ve tinh thanh ma benh nhan dang sinh song");
        }

    }
}
