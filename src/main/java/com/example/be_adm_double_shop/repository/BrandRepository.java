package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Brand;
import com.example.be_adm_double_shop.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query(value = "SELECT CODE FROM brand WHERE code = :code", nativeQuery = true)
    String checkCodeExits(@Param("code") String code);

    @Query(value = "SELECT * FROM brand WHERE code = :code", nativeQuery = true)
    Brand getBrandByCode(@Param("code") String code);

}
