package com.fpt.controller;

import com.fpt.model.LoaiXe;
import com.fpt.model.NhaXe;
import com.fpt.model.Xe;
import com.fpt.service.LoaiXeService;
import com.fpt.service.NhaXeService;
import com.fpt.service.XeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/xe")
public class XeController {
    private final XeService xeService;
    private final LoaiXeService loaiXeService;
    private final NhaXeService nhaXeService;

    @Autowired
    public XeController(LoaiXeService loaiXeService, XeService xeService, NhaXeService nhaXeService) {
        this.loaiXeService = loaiXeService;
        this.xeService = xeService;
        this.nhaXeService = nhaXeService;
    }

    @GetMapping
    public String showAddForm(Model model) {
        model.addAttribute("xe", new Xe());
        List<LoaiXe> listLoaiXe = loaiXeService.getLoaiXeList();
        List<NhaXe> listNhaXe = nhaXeService.getListNhaXe();
        model.addAttribute("listLoaiXe", listLoaiXe);
        model.addAttribute("listNhaXe", listNhaXe);
        return "addXe";
    }

    @PostMapping("/addXe")
    public String addXe(@ModelAttribute("xe") Xe xe,
                        RedirectAttributes redirectAttributes) {
        try {
            System.out.println("Dữ liệu Xe được submit:");
            System.out.println(xe.toString());
            xeService.createXe(xe);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm xe thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi thêm xe: " + e.getMessage());
            redirectAttributes.addFlashAttribute("xe", xe); // Giữ lại giá trị đã nhập
        }

        return "redirect:/xe";
    }
}