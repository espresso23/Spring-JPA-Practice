package com.fpt.controller;

import com.fpt.model.DangKyLamThem;
import com.fpt.service.DangKyLamThemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dangky")
public class DangKyLamThemController {

    @Autowired
    private DangKyLamThemService dangKyLamThemService;

    // Save DangKyLamThem
    @PostMapping("/dkLamThem")
    public ResponseEntity<DangKyLamThem> saveDangKyLamThem(@RequestBody DangKyLamThem dangKyLamThem) {
        DangKyLamThem savedDangKyLamThem = dangKyLamThemService.saveDangKyLamThem(dangKyLamThem);
        if (savedDangKyLamThem != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDangKyLamThem);  // Trả về mã 201 Created với đối tượng mới
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // Trả về mã 400 nếu không thành công
        }
    }

    // Get DangKyLamThem by ID
    @GetMapping("/{maDK}")
    public ResponseEntity<DangKyLamThem> getDangKyLamThemById(@PathVariable String maDK) {
        DangKyLamThem dangKyLamThem = dangKyLamThemService.getDangKyLamThemById(maDK);
        if (dangKyLamThem != null) {
            return ResponseEntity.ok(dangKyLamThem);  // Trả về mã 200 OK với đối tượng tìm thấy
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Trả về mã 404 Not Found nếu không tìm thấy
        }
    }

    // Update DangKyLamThem by ID
    @PutMapping("/{maDK}")
    public ResponseEntity<DangKyLamThem> updateDangKyLamThem(@PathVariable String maDK, @RequestBody DangKyLamThem dangKyLamThem) {
        DangKyLamThem updatedDangKyLamThem = dangKyLamThemService.updateDangKyLamThem(maDK, dangKyLamThem);
        if (updatedDangKyLamThem != null) {
            return ResponseEntity.ok(updatedDangKyLamThem);  // Trả về mã 200 OK với đối tượng đã cập nhật
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Trả về mã 404 Not Found nếu không tìm thấy
        }
    }

    // Delete DangKyLamThem by ID
    @DeleteMapping("/{maDK}")
    public ResponseEntity<Void> deleteDangKyLamThem(@PathVariable String maDK) {
        dangKyLamThemService.deleteDangKyLamThem(maDK);
        boolean stillExist = dangKyLamThemService.findByID(maDK) ? true : false;
        if (!stillExist) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // Trả về mã 204 No Content khi xóa thành công
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Trả về mã 404 Not Found nếu không tìm thấy
        }
    }

    // Get all DangKyLamThem
    @GetMapping
    public ResponseEntity<List<DangKyLamThem>> getAllDangKyLamThem() {
        List<DangKyLamThem> dangKyLamThemList = dangKyLamThemService.getAllDangKyLamThemNotClosed();
        if (dangKyLamThemList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // Trả về mã 204 No Content nếu không có dữ liệu
        } else {
            return ResponseEntity.ok(dangKyLamThemList);  // Trả về mã 200 OK cùng với danh sách đối tượng
        }
    }

    // Search DangKyLamThem
    @GetMapping("/search")
    public ResponseEntity<List<DangKyLamThem>> searchDangKyLamThem(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String searchType) {
        List<DangKyLamThem> results = dangKyLamThemService.searchDangKyLamThem(keyword, searchType);
        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // Trả về mã 204 No Content nếu không tìm thấy kết quả
        } else {
            return ResponseEntity.ok(results);  // Trả về mã 200 OK cùng với danh sách kết quả
        }
    }
}
