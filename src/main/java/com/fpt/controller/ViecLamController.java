package com.fpt.controller;

import com.fpt.model.ViecLam;
import com.fpt.service.ViecLamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vieclam")
public class ViecLamController {

    @Autowired
    private ViecLamService viecLamService;

    @GetMapping
    public String showPage(Model model) {
        model.addAttribute("viecLam", new ViecLam());
        model.addAttribute("danhSachViecLam", viecLamService.getAllViecLam()); // Thêm đối tượng rỗng cho form
        return "addViecLam";
    }

    @PostMapping("/addViecLam")
    public String addViecLam(@ModelAttribute("viecLam") ViecLam viecLam, RedirectAttributes redirectAttributes) {
        try {
            viecLamService.saveViecLam(viecLam);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm việc làm thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi " + e.getMessage());
        }
        return "redirect:/vieclam";
    }


}
