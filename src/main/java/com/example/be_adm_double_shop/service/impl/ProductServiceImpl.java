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

        sql.append(" SELECT p.name,b.name,cl.name, ct.name,m.name, c.name, s.name,dp.quantity, ");
        sql.append(" dp.status,dp.created_by,dp.created_time,dp.updated_by,dp.updated_time ");
        sql.append(" FROM product p JOIN detail_product dp ON p.id = dp.id_product ");
        sql.append(" JOIN brand b on dp.id_brand = b.id JOIN collar cl ON dp.id_collar = cl.id ");
        sql.append(" JOIN category ct on dp.id_category = ct.id JOIN material m on dp.id_category = m.id ");
        sql.append(" JOIN color c on dp.id_color = c.id JOIN size s on dp.id_size = s.id WHERE 1 = 1 ");


        Query query = entityManager.createNativeQuery(sql.toString());
        params.forEach(query::setParameter);


        return null;
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

}
