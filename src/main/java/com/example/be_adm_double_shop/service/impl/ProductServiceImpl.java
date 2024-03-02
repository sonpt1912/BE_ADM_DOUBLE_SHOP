package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.entity.Product;
import com.example.be_adm_double_shop.repository.ProductRepository;
import com.example.be_adm_double_shop.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getAllProductByCondition() {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();


        Query query = entityManager.createNativeQuery(sql.toString());
        params.forEach(query::setParameter);


        return null;
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

}
