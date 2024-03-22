package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.DetailProduct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailProductRepository extends JpaRepository<DetailProduct, Long> {

    @Query(value = "SELECT * from detail_product WHERE id_product = :id", nativeQuery = true)
    List<DetailProduct> getAllDetailProduct(@Param("id") Long id);

}
