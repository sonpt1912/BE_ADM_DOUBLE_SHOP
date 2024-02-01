package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {

    @Query(value = "SELECT CODE FROM size WHERE code = :code", nativeQuery = true)
    String checkCodeExits(@Param("code") String code);

    @Query(value = "SELECT * FROM size WHERE code = :code", nativeQuery = true)
    Size getSizeByCode(@Param("code") String code);

}
