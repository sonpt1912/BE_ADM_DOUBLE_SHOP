package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.dto.request.ProductRequest;
import com.example.be_adm_double_shop.entity.Product;

public interface ProductService {
    Object getAllProductByCondition(ProductRequest request);

    Object getAllProduct(ProductRequest request) throws Exception;

    Product getProductById(Long productId);

    Object createProduct(ProductRequest request, String token);

}
