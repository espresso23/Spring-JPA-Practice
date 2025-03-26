package com.fpt.controller;

import com.fpt.model.NhaXe;
import com.fpt.service.NhaXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/nhaxe")
public class NhaXeController {
    private NhaXeService nhaXeService;

    @Autowired
    public NhaXeController(NhaXeService nhaXeService) {
        this.nhaXeService = nhaXeService;
    }

    @GetMapping
    public String showNhaXe(@ModelAttribute("nhaXe") NhaXe nhaXe) {
        return "addNhaXe";
    }

    @PostMapping("/addNhaXe")
    public String createNhaXe(@ModelAttribute("nhaXe") NhaXe nhaXe, RedirectAttributes redirectAttributes) {
        try {
            nhaXeService.createNhaXe(nhaXe);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm nhà xe thành công!");
            return "redirect:/nhaxe";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/nhaxe";
    }
}
