package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByCode(String code);

}
