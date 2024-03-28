package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Product;
import com.example.be_adm_double_shop.entity.ProductView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductViewRepository extends JpaRepository<ProductView, Long> {

}
