package com.example.be_adm_double_shop.service;


import com.example.be_adm_double_shop.dto.ListResponse;
import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.entity.Size;

import java.util.List;

public interface SizeService {

    ListResponse<Size> getAllByConditon(SizeRequest request);

    String save(Size size);

    Object update(Size size);

}
