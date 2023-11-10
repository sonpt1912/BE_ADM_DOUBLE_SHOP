package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.entity.Material;
import com.example.be_adm_double_shop.repository.MaterialRepository;
import com.example.be_adm_double_shop.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public List<Material> getAll() {
        return materialRepository.findAll();
    }

    @Override
    public Material getOneById(Long id) {
        return materialRepository.findById(id).get();
    }

    @Override
    public Page getAllByPage(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return materialRepository.findAll(pageable);
    }

    @Override
    public Material save(Material material) {
        return materialRepository.save(material);
    }
}
