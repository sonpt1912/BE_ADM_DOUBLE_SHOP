package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.dto.request.ProductRequest;
import com.example.be_adm_double_shop.entity.Product;

import java.util.List;

public interface ProductService {
    Object getAllProductByCondition(ProductRequest request);

    Object getAllProduct(ProductRequest request) throws Exception;

    Object createProduct(ProductRequest request, String token) throws Exception;

    Object updateProduct(ProductRequest request, String username);

}
