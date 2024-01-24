package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    @Query("Select p from Promotion p order by p.id desc")
    List<Promotion> getAll();

    @Query("Select p from Promotion p where p.id = ?1")
    Promotion getOneById(long id);
}
