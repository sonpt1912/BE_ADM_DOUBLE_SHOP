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
import java.util.Optional;

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

    @Override
    public Size update(Size size, Long id) {
        Optional<Size> optional = sizeRepository.findById(id);
        return optional.map(o->{
            o.setCode(size.getCode());
            o.setName(size.getName());
            o.setDescription(size.getDescription());
            o.setStatus(size.getStatus());
            o.setCreatedBy(size.getCreatedBy());
            o.setUpdatedBy(size.getUpdatedBy());
            o.setCreatedTime(size.getCreatedTime());
            o.setUpdatedTime(size.getUpdatedTime());
            return  sizeRepository.save(size);
        }).orElse(null);
    }
}
