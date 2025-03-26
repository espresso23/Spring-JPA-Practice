package com.fpt.service;

import com.fpt.model.NhaXe;
import com.fpt.repository.NhaXeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NhaXeService {
    private NhaXeRepository nhaXeRepository;

    @Autowired
    public NhaXeService(NhaXeRepository nhaXeRepository) {
        this.nhaXeRepository = nhaXeRepository;
    }

    public List<NhaXe> getListNhaXe() {
        return nhaXeRepository.findAll();
    }

    public NhaXe createNhaXe(NhaXe nhaXe) {
        return nhaXeRepository.save(nhaXe);
    }

}
