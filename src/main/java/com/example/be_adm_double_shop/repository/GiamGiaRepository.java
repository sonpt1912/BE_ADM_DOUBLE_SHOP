package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Collar;
import com.example.be_adm_double_shop.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GiamGiaRepository extends JpaRepository<Promotion,Long> {
    @Query(value = "SELECT name FROM promotion WHERE name = :name", nativeQuery = true)
    String checkNameExits(@Param("name") String name);

    @Query(value = "SELECT * FROM promotion WHERE name = :name", nativeQuery = true)
    Promotion getPromotionByName(@Param("name") String name);
}
