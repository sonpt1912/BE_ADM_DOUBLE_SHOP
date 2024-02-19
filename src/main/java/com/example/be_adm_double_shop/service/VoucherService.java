package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.dto.request.VoucherRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Voucher;

public interface VoucherService {
    ListResponse<Voucher> getAll(VoucherRequest request);

    Voucher getOneId(Long id);

    String save(Voucher voucher, String username);
    Object update(Voucher voucherRequest, String username);

}
