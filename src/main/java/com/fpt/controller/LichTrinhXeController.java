package com.fpt.controller;

import com.fpt.model.LichTrinhXe;
import com.fpt.model.Xe;
import com.fpt.service.LichTrinhXeService;
import com.fpt.service.TuyenXeService;
import com.fpt.service.XeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/lichtrinhxe")
public class LichTrinhXeController {
    private final LichTrinhXeService lichTrinhXeService;
    private final TuyenXeService tuyenXeService;
    private final XeService xeService;

    @Autowired
    public LichTrinhXeController(LichTrinhXeService lichTrinhXeService, TuyenXeService tuyenXeService, XeService xeService) {
        this.lichTrinhXeService = lichTrinhXeService;
        this.tuyenXeService = tuyenXeService;
        this.xeService = xeService;
    }

    @GetMapping
    public String getLichTrinhXe(Model model) {
        // Thêm đối tượng lichTrinhXe mới vào model
        model.addAttribute("lichTrinhXe", new LichTrinhXe());
        // Thêm các danh sách cần thiết
        model.addAttribute("listTuyenXe", tuyenXeService.getTuyenXeList());
        model.addAttribute("listXe", xeService.getXeList());
        return "lich_trinh_xe";
    }
    @GetMapping("/list")
    public String getLichTrinhXeList(Model model) {
        List<LichTrinhXe> lichTrinhXeList = lichTrinhXeService.getLichTrinhXeList();
        List<Xe> xeList = xeService.getXeList();

        model.addAttribute("listLichTrinhXe", lichTrinhXeList != null ? lichTrinhXeList : Collections.emptyList());
        model.addAttribute("listXe", xeList != null ? xeList : Collections.emptyList());

        return "listLichTrinhXe";
    }
    @GetMapping("/search")
    public String getLichTrinhXeList(@RequestParam("tenNhaXe") String tenNhaXe,
                                     RedirectAttributes redirectAttributes) {
        List<LichTrinhXe> lichTrinhXeList = lichTrinhXeService.getLichTrinhByTenNhaXe(tenNhaXe);

        redirectAttributes.addFlashAttribute("listLichTrinhXe", lichTrinhXeList);
        redirectAttributes.addFlashAttribute("searchKeyword", tenNhaXe); // Lưu từ khóa tìm kiếm

        if (lichTrinhXeList.isEmpty()) {
            redirectAttributes.addFlashAttribute("notFoundMessage",
                    "Không tìm thấy lịch trình nào cho nhà xe: '" + tenNhaXe + "'");
        }

        return "redirect:/lichtrinhxe/list";
    }

    @GetMapping("/add/{maXe}")
    public String showAddFormWithXe(@PathVariable("maXe") String maXe, Model model) {
        // Lấy thông tin xe
        Xe xe = xeService.getXeById(maXe);
        if (xe == null) {
            return "redirect:/lichtrinhxe?notFoundMessage=Xe không tồn tại";
        }

        // Tạo đối tượng mới và set xe
        LichTrinhXe lichTrinhXe = new LichTrinhXe();
        lichTrinhXe.setXe(xe);

        // Thêm dữ liệu vào model
        model.addAttribute("lichTrinhXe", lichTrinhXe);
        model.addAttribute("listTuyenXe", tuyenXeService.getTuyenXeList());
        model.addAttribute("listXe", xeService.getXeList());

        // Trả về view thay vì redirect
        return "lich_trinh_xe"; // Tên file HTML của bạn
    }
    @PostMapping("/add")
    public String addLichTrinhXe(@ModelAttribute("lichTrinhXe") LichTrinhXe lichTrinhXe,
                                 RedirectAttributes redirectAttributes) {
        try {
            // Chuyển đổi từ input form sang LocalDateTime
            LocalDate ngay = LocalDate.parse(lichTrinhXe.getNgayXuatBenForm());
            LocalTime gio = LocalTime.parse(lichTrinhXe.getGioXuatBenForm());
            LocalDateTime gioXuatBen = LocalDateTime.of(ngay, gio);

            // Gán giá trị đã chuyển đổi
            lichTrinhXe.setNgayXuatBen(ngay);
            lichTrinhXe.setGioXuatBen(gioXuatBen);

            lichTrinhXeService.createLichTrinh(lichTrinhXe);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm lịch trình thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
            e.printStackTrace(); // Log lỗi để debug
        }
        return "redirect:/lichtrinhxe/list";
    }
}
