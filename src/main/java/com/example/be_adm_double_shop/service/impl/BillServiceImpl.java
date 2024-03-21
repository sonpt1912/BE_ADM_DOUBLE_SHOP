package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.request.BillRequest;
import com.example.be_adm_double_shop.entity.DetailBill;
import com.example.be_adm_double_shop.repository.BillRepository;
import com.example.be_adm_double_shop.service.BillService;
import com.example.be_adm_double_shop.service.DetailProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private DetailProductService detailProductService;



    @Override
    public Object createBill(BillRequest billRequest, String creator) {
        return null;
    }

    @Override
    public Object updateBill(BillRequest billRequest, String creator) {
        return null;
    }
}
