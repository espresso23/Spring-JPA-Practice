package com.fpt.controller;

import com.fpt.model.ViecLam;
import com.fpt.service.ViecLamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vieclam")
public class ViecLamController {

    @Autowired
    private ViecLamService viecLamService;

    @PostMapping
    public ViecLam addViecLam(@RequestBody ViecLam viecLam) {
        return viecLamService.saveViecLam(viecLam);
    }

    @GetMapping
    public List<ViecLam> getAllViecLam() {
        return viecLamService.getAllViecLam();
    }

}
