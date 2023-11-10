package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.entity.Collar;
import com.example.be_adm_double_shop.repository.CollarRepository;
import com.example.be_adm_double_shop.service.CollarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollarServiceImpl implements CollarService {

    @Autowired
    private CollarRepository collarRepository;

    @Override
    public List<Collar> getAll() {
        return collarRepository.findAll();
    }

    @Override
    public Collar getOneById(Long id) {
        return collarRepository.findById(id).get();
    }

    @Override
    public Page getAllByPage(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return collarRepository.findAll(pageable);
    }

    @Override
    public Collar save(Collar collar) {
        return collarRepository.save(collar);
    }
}
