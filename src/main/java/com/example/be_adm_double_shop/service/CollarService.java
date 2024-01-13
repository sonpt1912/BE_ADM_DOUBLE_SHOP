package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Collar;
import com.example.be_adm_double_shop.entity.Size;

public interface CollarService {
    ListResponse<Collar> getAllByConditon(SizeRequest request);

    String save(Collar collar);

    Object update(Collar collar);
}
