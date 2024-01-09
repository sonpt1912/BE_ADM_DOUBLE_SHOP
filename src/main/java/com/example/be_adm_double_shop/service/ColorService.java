package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.dto.request.ColorRequest;
import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Color;
import com.example.be_adm_double_shop.entity.Size;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ColorService {

    ListResponse<Color> getAll(ColorRequest request);

    Color getOneId(Long id);
Color delete(Long id);
    Page getAllByPage(int page, int pageSize);

    Color save(Color color);
    Color update(Color color, Long id);

}
