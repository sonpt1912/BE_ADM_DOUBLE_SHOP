package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankRepository  extends JpaRepository<Rank,Long> {
    List<Rank> findByNameContainingIgnoreCase(String name);
    List<Rank> findAllByOrderByName();

}
