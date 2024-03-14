package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.dto.request.BrandRequest;
import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Brand;
import com.example.be_adm_double_shop.entity.Size;

public interface BrandService {

    ListResponse<Brand> getAllByConditon(BrandRequest request);

    String save(Brand brand, String username);

    Object update(Brand brand, String username);

}
