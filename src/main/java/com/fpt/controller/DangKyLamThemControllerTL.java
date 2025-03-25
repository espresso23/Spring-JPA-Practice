package com.fpt.controller;

import com.fpt.model.DangKyLamThem;
import com.fpt.service.DangKyLamThemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/dangky")
public class DangKyLamThemControllerTL {
    @Autowired
    private DangKyLamThemService dangKyLamThemService;

    // Hiển thị danh sách đăng ký làm thêm
    @GetMapping
    public String getAllDangKyLamThem(Model model) {
        try {
            // Kiểm tra xem đã có danh sách từ search chưa
            if (!model.containsAttribute("dangKyLamThemList")) {
                List<DangKyLamThem> dangKyLamThemList = dangKyLamThemService.getAllDangKyLamThemNotClosed();
                model.addAttribute("dangKyLamThemList", dangKyLamThemList);
            }
            model.addAttribute("dangKyLamThem", new DangKyLamThem());
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    // Hiển thị form thêm mới đăng ký làm thêm
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("dangKyLamThem", new DangKyLamThem());
        return "index"; // Trả về trang index.html
    }

    @PostMapping("/save")
    public String saveDangKyLamThem(
            @Valid @ModelAttribute DangKyLamThem dangKyLamThem,  // Thêm @Valid
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // Giữ lại giá trị form và lỗi khi redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.dangKyLamThem", result);
            redirectAttributes.addFlashAttribute("dangKyLamThem", dangKyLamThem);
            return "redirect:/dangky";
        }

        try {
            dangKyLamThemService.saveDangKyLamThem(dangKyLamThem);
            redirectAttributes.addFlashAttribute("successMessage", "Đăng ký thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi hệ thống: " + e.getMessage());
        }
        return "redirect:/dangky";
    }
    @GetMapping("/search")
    public String search(
            @RequestParam("keyword") String keyword,
            @RequestParam("searchType") String searchType,
            RedirectAttributes redirectAttributes) {

        System.out.println("Search keyword: " + keyword);
        System.out.println("Search type: " + searchType);

        List<DangKyLamThem> dangKyLamThemList = dangKyLamThemService.searchDangKyLamThem(keyword, searchType);
        redirectAttributes.addFlashAttribute("dangKyLamThemList", dangKyLamThemList);
        // Thêm keyword và searchType để hiển thị lại trên form
        redirectAttributes.addFlashAttribute("keyword", keyword);
        redirectAttributes.addFlashAttribute("searchType", searchType);
        return "redirect:/dangky";
    }



    @GetMapping("/list")
    public String showList(Model model) {
        if (!model.containsAttribute("dangKyLamThemList")) {
            model.addAttribute("dangKyLamThemList", dangKyLamThemService.getAllDangKyLamThemNotClosed());
        }
        return "index";
    }

    @GetMapping("/edit/{maDK}")
    public String showEditForm(@PathVariable String maDK, Model model) {
        DangKyLamThem dangKyLamThem = dangKyLamThemService.getDangKyLamThemById(maDK);
        model.addAttribute("dangKyLamThem", dangKyLamThem);
        return "edit"; // Trả về template edit.html
    }

    @PostMapping("/update/{maDK}")
    public String updateDangKyLamThem(@PathVariable String maDK, @ModelAttribute DangKyLamThem dangKyLamThem) {
        dangKyLamThemService.updateDangKyLamThem(maDK, dangKyLamThem);
        return "redirect:/dangky";
    }

    // Xóa đăng ký làm thêm
    @GetMapping("/delete/{maDK}")
    public String deleteDangKyLamThem(@PathVariable String maDK) {
        dangKyLamThemService.deleteDangKyLamThem(maDK);
        return "redirect:/dangky"; // Chuyển hướng về danh sách sau khi xóa
    }
}
