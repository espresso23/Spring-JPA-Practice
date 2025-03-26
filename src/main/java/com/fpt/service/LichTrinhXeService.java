package com.fpt.service;

import com.fpt.model.LichTrinhXe;
import com.fpt.model.LichTrinhXeId;
import com.fpt.model.TuyenXe;
import com.fpt.model.Xe;
import com.fpt.repository.LichTrinhXeRepository;
import com.fpt.repository.TuyenXeRepository;
import com.fpt.repository.XeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LichTrinhXeService {
    private final LichTrinhXeRepository lichTrinhXeRepository;
    private final XeRepository xeRepository;
    private final TuyenXeRepository tuyenXeRepository;

    @Autowired
    public LichTrinhXeService(LichTrinhXeRepository lichTrinhXeRepository, XeRepository xeRepository, TuyenXeRepository tuyenXeRepository) {
        this.lichTrinhXeRepository = lichTrinhXeRepository;
        this.xeRepository = xeRepository;
        this.tuyenXeRepository = tuyenXeRepository;
    }

    public LichTrinhXe createLichTrinh(LichTrinhXe lichTrinh) {
        // Validate input
        if (lichTrinh == null) {
            throw new IllegalArgumentException("Thông tin lịch trình không được null");
        }
        if (lichTrinh.getXe() == null || lichTrinh.getXe().getMaXe() == null) {
            throw new IllegalArgumentException("Mã xe không được để trống");
        }
        if (lichTrinh.getTuyenXe() == null || lichTrinh.getTuyenXe().getMaTuyen() == null) {
            throw new IllegalArgumentException("Mã tuyến xe không được để trống");
        }
        if (lichTrinh.getNgayXuatBen() == null) {
            throw new IllegalArgumentException("Ngày xuất bến không được để trống");
        }
        if (lichTrinh.getGioXuatBen() == null) {
            throw new IllegalArgumentException("Giờ xuất bến không được để trống");
        }

        // Kiểm tra xe tồn tại
        Xe xe = xeRepository.findById(lichTrinh.getXe().getMaXe())
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy xe với mã: " + lichTrinh.getXe().getMaXe()));

        // Kiểm tra tuyến xe tồn tại
        TuyenXe tuyenXe = tuyenXeRepository.findById(lichTrinh.getTuyenXe().getMaTuyen())
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy tuyến xe với mã: " + lichTrinh.getTuyenXe().getMaTuyen()));

        // Kiểm tra trùng lịch trình
        LichTrinhXeId id = new LichTrinhXeId(
                lichTrinh.getXe().getMaXe(),
                lichTrinh.getNgayXuatBen(),
                lichTrinh.getGioXuatBen()
        );

        if (lichTrinhXeRepository.existsById(id)) {
            throw new IllegalStateException("Đã tồn tại lịch trình với cùng mã xe, ngày và giờ xuất bến");
        }

        // Thiết lập quan hệ
        lichTrinh.setXe(xe);
        lichTrinh.setTuyenXe(tuyenXe);

        // Đảm bảo ID được thiết lập đúng
        lichTrinh.setNgayXuatBen(lichTrinh.getNgayXuatBen());
        lichTrinh.setGioXuatBen(lichTrinh.getGioXuatBen());

        return lichTrinhXeRepository.save(lichTrinh);
    }

    public LichTrinhXe updateLichTrinh(LichTrinhXeId id, LichTrinhXe lichTrinh) {
        // Kiểm tra lịch trình tồn tại
        LichTrinhXe existing = lichTrinhXeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy lịch trình với ID: " + id));

        // Cập nhật thông tin
        existing.setTenTaiXe(lichTrinh.getTenTaiXe());
        existing.setSoLuongHanhKhach(lichTrinh.getSoLuongHanhKhach());

        // Nếu thay đổi tuyến xe
        if (!existing.getTuyenXe().getMaTuyen().equals(lichTrinh.getTuyenXe().getMaTuyen())) {
            TuyenXe tuyenXe = tuyenXeRepository.findById(lichTrinh.getTuyenXe().getMaTuyen())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy tuyến xe với mã: " + lichTrinh.getTuyenXe().getMaTuyen()));
            existing.setTuyenXe(tuyenXe);
        }

        return lichTrinhXeRepository.save(existing);
    }

    public void deleteLichTrinh(LichTrinhXeId id) {
        LichTrinhXe lichTrinh = getLichTrinhById(id);
        lichTrinhXeRepository.delete(lichTrinh);
    }

    public LichTrinhXe getLichTrinhById(LichTrinhXeId id) {
        return lichTrinhXeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch trình với ID: " + id));
    }

    public List<LichTrinhXe> getLichTrinhXeList() {
        return lichTrinhXeRepository.findAll();
    }

    public List<LichTrinhXe> getLichTrinhByTenNhaXe(String tenNhaXe) {
        return lichTrinhXeRepository.findLichTrinhXeByNhaXe(tenNhaXe);
    }
}
