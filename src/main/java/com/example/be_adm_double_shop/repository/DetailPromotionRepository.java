package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.DetailPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailPromotionRepository extends JpaRepository<DetailPromotion, Long> {
}
