package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Brand;
import com.example.be_adm_double_shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT CODE FROM category WHERE code = :code", nativeQuery = true)
    String checkCodeExits(@Param("code") String code);

    @Query(value = "SELECT * FROM category WHERE code = :code", nativeQuery = true)
    Category getCategoryByCode(@Param("code") String code);

}
