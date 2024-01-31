package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Collar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CollarRepository extends JpaRepository<Collar,Long> {
    @Query(value = "SELECT CODE FROM collar WHERE code = :code", nativeQuery = true)
    String checkCodeExits(@Param("code") String code);

    @Query(value = "SELECT * FROM collar WHERE code = :code", nativeQuery = true)
    Collar getCollarByCode(@Param("code") String code);
}
