package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.entity.Color;
import com.example.be_adm_double_shop.repository.ColorRepository;
import com.example.be_adm_double_shop.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository repository;


    @Override
    public List<Color> getAll() {
        return repository.findAll();
    }

    @Override
    public Color getOneById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Page getAllByPage(int page, int pageSize) {
        Pageable p = PageRequest.of(page, pageSize);
        return repository.findAll(p);
    }

    @Override
    public Color save(Color color) {
        return repository.save(color);
    }
}
