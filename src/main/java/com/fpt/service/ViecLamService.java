//package com.fpt.service;
//
//import com.fpt.repository.ViecLamRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ViecLamService {
//    @Autowired
//    private ViecLamRepository viecLamRepository;
//
//    public List<ViecLam> getAllViecLam() {
//        return viecLamRepository.findAll();
//    }
//    public ViecLam saveViecLam(ViecLam viecLam) {
//        // Validate dữ liệu nếu cần (ví dụ: mã việc làm không trùng)
//        if (viecLamRepository.existsById(viecLam.getMaVL())) {
//            throw new RuntimeException("Ma viec lam da ton tai");
//        }
//        return viecLamRepository.save(viecLam);
//    }
//
//    public ViecLam getViecLamById(String maVL) {
//        return viecLamRepository.findById(maVL).orElse(null);
//    }
//}
