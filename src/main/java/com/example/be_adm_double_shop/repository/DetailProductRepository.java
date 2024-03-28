package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.DetailProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailProductRepository extends JpaRepository<DetailProduct, Long> {

    @Query(value = " SELECT dt.id, " +
            "       dt.id_color," +
            "       dt.id_product, " +
            "       dt.id_size, " +
            "       dt.id_brand, " +
            "       dt.id_collar, " +
            "       dt.id_category, " +
            "       dt.id_material, " +
            "       dt.quantity, " +
            "       dt.status, " +
            "       dt.created_by, " +
            "       dt.created_time, " +
            "       dt.updated_by, " +
            "       dt.updated_time, " +
            "       dt.price, " +
            " CASE      WHEN (p.discount_amount != null)" +
            "               THEN dt.price - p.discount_amount" +
            "           WHEN (p.discount_percent != null)" +
            "               THEN dt.price * p.discount_percent" +
            "           ELSE 0" +
            "           END AS 'discout_amout'" +
            " FROM detail_product dt " +
            "         LEFT JOIN detail_promotion dp on dt.id = dp.id_detail_product " +
            "         LEFT JOIN promotion p on dp.id_promotion = p.id " +
            " WHERE dt.id = :id AND status = 0 AND p.status = 0 ", nativeQuery = true)
    List<DetailProduct> getAllDetailProduct(@Param("id") Long id);
}
