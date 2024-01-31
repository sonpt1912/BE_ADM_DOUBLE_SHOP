package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Collar;


public interface CollarService {
    ListResponse<Collar> getAll(SizeRequest request);

    Collar getOneId(Long id);
    Collar delete(Long id );


   String save(Collar collar,String username);
    Object update(Collar collarRequest,String username);
}
