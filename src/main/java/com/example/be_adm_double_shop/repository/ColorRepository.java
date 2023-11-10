package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.dto.ColorCustom;
import com.example.be_adm_double_shop.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    @Query(value = "select color.code, color.name, color.description, color.status from Color color", nativeQuery = true)
    List<ColorCustom> getAll();
}
