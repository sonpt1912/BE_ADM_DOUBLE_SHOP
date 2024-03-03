package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProductByCondition();

     Product getProductById(Long productId);

}
