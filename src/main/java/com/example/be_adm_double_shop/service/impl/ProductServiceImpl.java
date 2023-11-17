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
import java.util.Optional;

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

    @Override
    public Product update(Product product, Long id) {
        Optional<Product> optional = productRepository.findById(id);
        return optional.map(o -> {
            o.setName(product.getName());
            o.setStatus(product.getStatus());
            o.setCreatedBy(product.getCreatedBy());
            o.setUpdatedBy(product.getUpdatedBy());
            o.setCreatedTime(product.getCreatedTime());
            o.setUpdatedTime(product.getUpdatedTime());
            return productRepository.save(product);
        }).orElse(null);
    }
}
