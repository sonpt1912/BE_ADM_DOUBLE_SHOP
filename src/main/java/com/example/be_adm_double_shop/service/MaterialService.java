package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.Material;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MaterialService {

    List<Material> getAll();

    Material getOneById(Long id);

    Page getAllByPage(int page);

    Material save(Material material);

    Material update(Material material, Long id);


}
