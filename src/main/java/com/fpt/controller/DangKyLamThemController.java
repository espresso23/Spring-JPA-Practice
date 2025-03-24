package com.fpt.controller;

import com.fpt.model.DangKyLamThem;
import com.fpt.service.DangKyLamThemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dangky")
public class DangKyLamThemController {

    @Autowired
    private DangKyLamThemService dangKyLamThemService;

    @PostMapping("/dkLamThem")
    public DangKyLamThem saveDangKyLamThem(@RequestBody DangKyLamThem dangKyLamThem) {
        return dangKyLamThemService.saveDangKyLamThem(dangKyLamThem);
    }

    @GetMapping("/{maDK}")
    public DangKyLamThem getDangKyLamThemById(@PathVariable String maDK) {
        return dangKyLamThemService.getDangKyLamThemById(maDK);
    }

    @PutMapping("/{maDK}")
    public DangKyLamThem updateDangKyLamThem(@PathVariable String maDK, @RequestBody DangKyLamThem dangKyLamThem) {
        return dangKyLamThemService.updateDangKyLamThem(maDK, dangKyLamThem);
    }

    @DeleteMapping("/{maDK}")
    public void deleteDangKyLamThem(@PathVariable String maDK) {
        dangKyLamThemService.deleteDangKyLamThem(maDK);
    }

    @GetMapping
    public List<DangKyLamThem> getAllDangKyLamThem() {
        return dangKyLamThemService.getAllDangKyLamThemNotClosed();
    }

    // API tìm kiếm
    @GetMapping("/search")
    public List<DangKyLamThem> searchDangKyLamThem(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String searchType) {
        return dangKyLamThemService.searchDangKyLamThem(keyword, searchType);
    }
}