package com.fpt.service;

import com.fpt.model.TuyenXe;
import com.fpt.repository.TuyenXeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TuyenXeService {
    private TuyenXeRepository repository;

    @Autowired
    public TuyenXeService(TuyenXeRepository repository) {
        this.repository = repository;
    }

    public List<TuyenXe> getTuyenXeList() {
        return repository.findAll();
    }

    public TuyenXe getTuyenXeById(String id) {
        return repository.findById(id).orElse(null);
    }

    public TuyenXe saveTuyenXe(TuyenXe tuyenXe) {
        return repository.save(tuyenXe);
    }
}
