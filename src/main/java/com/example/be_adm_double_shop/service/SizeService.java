package com.example.be_adm_double_shop.service;


import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.entity.Size;

public interface SizeService {

    ListResponse<Size> getAllByConditon(SizeRequest request);

    String save(Size size, String username);

    Object update(Size size, String username);

}
