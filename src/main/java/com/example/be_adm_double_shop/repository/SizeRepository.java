package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {

    @Query(value = "SELECT CODE FROM Size WHERE code = :code", nativeQuery = true)
    String findSizeByCode(@Param("code") String code);

}
