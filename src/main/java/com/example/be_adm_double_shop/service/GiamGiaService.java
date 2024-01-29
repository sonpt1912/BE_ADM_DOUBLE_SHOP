package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.dto.request.GiamGiaRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Promotion;

public interface GiamGiaService {
    ListResponse<Promotion> getAll(GiamGiaRequest request);

    Promotion getOneId(Long id);
    Promotion delete(Long id );

    String save(Promotion promotion,String username);
    Object update(Promotion promotion,String username);
}
