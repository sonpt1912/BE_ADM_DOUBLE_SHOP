package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.dto.ColorCustom;
import com.example.be_adm_double_shop.dto.ColorRequest;
import com.example.be_adm_double_shop.entity.Color;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ColorService {

    List<ColorCustom> list();

    Color getOneById(Long id);

    Page getAllByPage(int page);

    Color save(ColorRequest colorRequest);

    Color delete(Long id);

    Color update(ColorRequest colorRequest, Long id);

}
