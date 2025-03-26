package com.fpt.service;

import com.fpt.model.LoaiXe;
import com.fpt.repository.LoaiXeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LoaiXeService {

    private LoaiXeRepository loaiXeRepository;

    @Autowired
    public LoaiXeService(LoaiXeRepository loaiXeRepository) {
        this.loaiXeRepository = loaiXeRepository;
    }

    public LoaiXe createLoaiXe(LoaiXe loaiXe) {
        try {
            return loaiXeRepository.save(loaiXe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<LoaiXe> getLoaiXeList() {
        return loaiXeRepository.findAll();
    }
}
