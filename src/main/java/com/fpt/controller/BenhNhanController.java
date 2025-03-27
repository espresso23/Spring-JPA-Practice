package com.fpt.controller;

import com.fpt.model.BenhNhan;
import com.fpt.service.BenhNhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/benhnhan")
public class BenhNhanController {
    private BenhNhanService benhNhanService;

    @Autowired
    public BenhNhanController(BenhNhanService benhNhanService) {
        this.benhNhanService = benhNhanService;
    }

    @GetMapping
    public String showPageBenhNhan(Model model) {
        model.addAttribute("benhNhan", new BenhNhan());

        return "addBenhNhan";
    }

    @PostMapping("/addBenhNhan")
    public String addBenhNhan(@ModelAttribute("benhNhan") BenhNhan benhNhan, RedirectAttributes redirectAttributes) {

        try {
            benhNhanService.createBenhNhan(benhNhan);
            redirectAttributes.addFlashAttribute("successMessage", "Add Benh Nhan successfully");
            return "redirect:/benhnhan/list";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/list")
    public String showListBenhNhan(Model model) {
        List<BenhNhan> listBenhNhan = benhNhanService.getAllBenhNhan();
        model.addAttribute("listBenhNhan", listBenhNhan);
        return "listBenhNhan";
    }

    @GetMapping("/search")
    public String getLichTrinhXeList(@RequestParam("soCMND") String soCMND,
                                     RedirectAttributes redirectAttributes) {
        List<BenhNhan> benhNhanList = benhNhanService.getBenhNhansBySoCMND(soCMND);

        redirectAttributes.addFlashAttribute("listBenhNhan", benhNhanList);
        redirectAttributes.addFlashAttribute("searchKeyword", soCMND); // Lưu từ khóa tìm kiếm

        if (benhNhanList.isEmpty()) {
            redirectAttributes.addFlashAttribute("notFoundMessage",
                    "Không tìm thấy bệnh nhân với số: '" + soCMND + "'");
        }

        return "redirect:/benhnhan/list";
    }

    @GetMapping("/edit/{maBenhNhan}")
    public String showFormEdit(@PathVariable("maBenhNhan") String maBenhNhan, Model model) {
        // Lấy thông tin xe
        BenhNhan benhNhan = benhNhanService.getBenhNhanById(maBenhNhan);
        if (benhNhan == null) {
            return "redirect:/benhnhan?notFoundMessage=Bệnh nhân không tồn tại";
        }
        // Thêm dữ liệu vào model
        model.addAttribute("benhNhan", benhNhan);

        // Trả về view thay vì redirect
        return "editBenhNhan"; // Tên file HTML của bạn
    }

    @PostMapping("/edit")
    public String editBenhNhan(@RequestParam("maBenhNhan") String maBenhNhan, @ModelAttribute("benhNhan") BenhNhan benhNhan, RedirectAttributes redirectAttributes) {
        System.out.println(maBenhNhan);
        try {
            benhNhanService.updateBenhNhan(maBenhNhan, benhNhan);
            redirectAttributes.addFlashAttribute("successMessage", "Updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/benhnhan/edit/" + maBenhNhan;
    }
}
