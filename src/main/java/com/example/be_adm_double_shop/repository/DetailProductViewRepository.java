package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.DetailProduct;
import com.example.be_adm_double_shop.entity.DetailProductView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailProductViewRepository extends JpaRepository<DetailProductView, Long> {

    @Query(value = "SELECT * from detail_product WHERE id_product = :id", nativeQuery = true)
    List<DetailProduct> getAllDetailProduct(@Param("id") Long id);

    @Query(value = "SELECT * FROM detail_product dp WHERE id_product = :id AND status = 0", nativeQuery = true)
    List<DetailProduct> getActiveDetailProduct(@Param("id") Long id);

}
