package com.fpt.controller;

import com.fpt.model.LichTrinhXe;
import com.fpt.service.LichTrinhXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/thongke")
public class ThongKeController {

    @Autowired
    private LichTrinhXeService lichTrinhXeService;

    @GetMapping
    public String thongKeNhaXe(Model model) {
        List<LichTrinhXe> allLichTrinh = lichTrinhXeService.getLichTrinhXeList();

        // Tính toán tổng thu nhập theo nhà xe
        Map<String, Double> thuNhapNhaXe = new HashMap<>();

        for (LichTrinhXe lichTrinh : allLichTrinh) {
            String tenNhaXe = lichTrinh.getXe().getNhaXe().getTenNhaXe();
            double thuNhap = lichTrinh.getTuyenXe().getDonGia() * lichTrinh.getSoLuongHanhKhach();

            thuNhapNhaXe.merge(tenNhaXe, thuNhap, Double::sum);
        }

        model.addAttribute("thuNhapNhaXe", thuNhapNhaXe);
        return "thongke_nhaxe";
    }
}