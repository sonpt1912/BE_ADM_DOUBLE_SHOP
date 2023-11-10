package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.entity.Product;
import com.example.be_adm_double_shop.repository.ProductRepository;
import com.example.be_adm_double_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getOneById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Page getAllByPage(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return productRepository.findAll(pageable);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
