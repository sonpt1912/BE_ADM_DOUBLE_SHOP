package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product getOneById(Long id);

    Page getAllByPage(int page);

    Product save(Product product);

    Product update(Product product, Long id);

}
