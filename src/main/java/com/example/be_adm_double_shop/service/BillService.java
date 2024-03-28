package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.dto.request.BillRequest;

public interface BillService {

    Object createBill(BillRequest billRequest, String creator);

    Object updateBill(BillRequest billRequest, String creator);

    Object getAllByCondition(BillRequest billRequest);

}
