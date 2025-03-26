package com.fpt.service;

import com.fpt.model.Xe;
import com.fpt.repository.XeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class XeService {
    private final XeRepository xeRepository;

    @Autowired
    public XeService(XeRepository xeRepository) {
        this.xeRepository = xeRepository;
    }

    public Xe createXe(Xe xe) {
        if (xeRepository.existsById(xe.getMaXe())) {
            throw new IllegalArgumentException("Mã xe đã tồn tại");
        }
        validateHanKiemDinh(xe.getHanKiemDinh());
        return xeRepository.save(xe);
    }

    private void validateHanKiemDinh(LocalDate hanKiemDinh) {
        if (hanKiemDinh == null) {
            throw new IllegalArgumentException("Hạn kiểm định không được để trống");
        }

        LocalDate now = LocalDate.now();
        LocalDate minimumValidDate = now.plusMonths(1);

        if (!hanKiemDinh.isAfter(minimumValidDate)) {
            throw new IllegalArgumentException(
                    "Hạn kiểm định không đúng, hạn kiểm định phải lớn hơn thời gian hiện tại là 1 tháng. " +
                            "Xin hãy nhập lại thông tin hạn kiểm định"
            );
        }
    }

    public List<Xe> getXeList() {
        return xeRepository.findAll();
    }

    public Xe getXeById(String id) {
        return xeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Xe không tồn tại"));
    }
}
