package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.Color;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ColorService {

    List<Color> getAll();

    Color getOneById(Long id);

    Page getAllByPage(int page, int pageSize);

    Color save(Color color);

}
