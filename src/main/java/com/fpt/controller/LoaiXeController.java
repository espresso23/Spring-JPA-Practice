package com.fpt.controller;

import com.fpt.model.LoaiXe;
import com.fpt.service.LoaiXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/loaixe")
public class LoaiXeController {
    private LoaiXeService loaiXeService;

    @Autowired
    public LoaiXeController(LoaiXeService loaiXeService) {
        this.loaiXeService = loaiXeService;
    }

    @GetMapping
    public String showPageLoaiXe(@ModelAttribute("loaiXe") LoaiXe loaiXe) {
        return "addLoaiXe";
    }

    @PostMapping("/addLoaiXe")
    public String createNhaXe(@ModelAttribute("loaiXe") LoaiXe loaiXe, RedirectAttributes redirectAttributes) {
        try {
            loaiXeService.createLoaiXe(loaiXe);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm loại xe thành công!");
            return "redirect:/loaixe";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/loaixe";
    }
}
