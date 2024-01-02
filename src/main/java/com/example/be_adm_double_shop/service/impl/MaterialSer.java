package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.Material;
import com.example.be_adm_double_shop.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialSer {
    @Autowired
    private MaterialRepository materialRepository;

    public List<Material> getAll() {
        return materialRepository.findAll();
    }

    public List<Material> phanTrang(Integer viTri) {
        Pageable p = PageRequest.of(viTri, 5);
        return materialRepository.findAll(p).toList();
    }

    public Material add_update(Material m){
        return materialRepository.save(m);
    }

    public void delete(Long id) {
        materialRepository.deleteById(id);
    }
}
