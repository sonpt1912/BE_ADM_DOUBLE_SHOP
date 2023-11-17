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
    private ColorRepository colorRepository;

    @Override
    public List<Color> getAll() {
        return colorRepository.findAll();
    }

    @Override
    public Color getOneById(Long id) {
        return colorRepository.findById(id).get();
    }

    @Override
    public Page getAllByPage(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return colorRepository.findAll(pageable);
    }

    @Override
    public Color save(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public Color update(Color color, Long id) {
        Optional<Color> optional = colorRepository.findById(id);
        return optional.map(o->{
            o.setCode(color.getCode());
            o.setName(color.getName());
            o.setDescription(color.getDescription());
            o.setStatus(color.getStatus());
            o.setCreatedBy(color.getCreatedBy());
            o.setUpdatedBy(color.getUpdatedBy());
            o.setCreatedTime(color.getCreatedTime());
            o.setUpdatedTime(color.getUpdatedTime());
            return colorRepository.save(color);
        }).orElse(null);
    }
}
