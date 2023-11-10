package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.entity.Size;
import com.example.be_adm_double_shop.repository.SizeRepository;
import com.example.be_adm_double_shop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<Size> getAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size getOneById(Long id) {
        return sizeRepository.findById(id).get();
    }

    @Override
    public Page getAllByPage(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return sizeRepository.findAll(pageable);
    }

    @Override
    public Size save(Size size) {
        return sizeRepository.save(size);
    }
}
