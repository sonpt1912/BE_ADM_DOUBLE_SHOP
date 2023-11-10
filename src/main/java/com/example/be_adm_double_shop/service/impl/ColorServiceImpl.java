package com.example.be_adm_double_shop.service.impl;


import com.example.be_adm_double_shop.dto.ColorCustom;
import com.example.be_adm_double_shop.dto.ColorRequest;
import com.example.be_adm_double_shop.entity.Color;
import com.example.be_adm_double_shop.repository.ColorRepository;
import com.example.be_adm_double_shop.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository repository;


    @Override
    public List<ColorCustom> list() {
        return repository.getAll();
    }

    @Override
    public Color getOneById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page getAllByPage(int page) {
        Pageable p = PageRequest.of(page, 5);
        return repository.findAll(p);
    }

    @Override
    public Color save(ColorRequest colorRequest) {
        Color color = colorRequest.map(new Color());
        return repository.save(color);
    }

    @Override
    public Color delete(Long id) {
        Optional<Color> colorOptional = repository.findById(id);
        return colorOptional.map(o ->{
            repository.delete(o);
            return o;
        }).orElse(null);
    }

    @Override
    public Color update(ColorRequest colorRequest, Long id) {
        Optional<Color> colorOptional = repository.findById(id);
        return colorOptional.map(o ->{
            o.setCode(colorRequest.getCode());
            o.setName(colorRequest.getName());
            o.setDescription(colorRequest.getDescription());
            o.setStatus(colorRequest.getStatus());
            return repository.save(o);
        }).orElse(null);
    }
}
