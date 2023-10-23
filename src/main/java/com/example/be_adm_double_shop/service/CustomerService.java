package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.Customer;
import com.example.be_adm_double_shop.repository.NhanVienRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class NhanVienService {

    private final NhanVienRepository nhanVienRepository;

    @Autowired
    public NhanVienService(NhanVienRepository nhanVienRepository) {
        this.nhanVienRepository = nhanVienRepository;
    }

    public List<Customer> sapXep() {
        return nhanVienRepository.findAllByOrderBySomeField(); // Replace with the actual field to sort by
    }

    public List<Customer> searchNhanVien(String keyword) {
        return nhanVienRepository.findByTenContainingIgnoreCase(keyword);
    }

    public Page<Customer> findAll(Pageable pageable) {
        return nhanVienRepository.findAll(pageable);
    }

    public List<Customer> getAll() {
        return nhanVienRepository.findAll();
    }

    public Customer save(Customer nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    public Customer updateNhanVien(UUID id, Customer nhanVien) throws ResourceNotFoundException {
        Optional<Customer> optionalNhanVien = nhanVienRepository.findById(id);
        if (optionalNhanVien.isPresent()) {
            Customer existingNhanVien = optionalNhanVien.get();
            existingNhanVien.setTen(nhanVien.getTen());
            existingNhanVien.setChucVu(nhanVien.getChucVu());
            existingNhanVien.setNgaySinh(nhanVien.getNgaySinh());
            existingNhanVien.setUpdateAt(LocalDateTime.now());
            // Cập nhật các thuộc tính khác của NhanVien

            return nhanVienRepository.save(existingNhanVien);
        }
        throw new ResourceNotFoundException("NhanVien not found with id: " + id);
    }

    public void deleteById(UUID id) {
        nhanVienRepository.deleteById(id);
    }

    public Customer findById(UUID id) throws ResourceNotFoundException {
        Optional<Customer> optionalNhanVien = nhanVienRepository.findById(id);
        if (optionalNhanVien.isPresent()) {
            return optionalNhanVien.get();
        }
        throw new ResourceNotFoundException("NhanVien not found with id: " + id);
    }
}
