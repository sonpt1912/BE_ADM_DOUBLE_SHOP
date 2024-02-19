package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Color;
import com.example.be_adm_double_shop.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankRepository extends JpaRepository<Rank, Long> {
}
