package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.dto.request.DetailProductRequest;
import com.example.be_adm_double_shop.entity.Bill;
import com.example.be_adm_double_shop.entity.DetailBill;
import com.example.be_adm_double_shop.entity.DetailProduct;

import java.util.List;

public interface DetailBillService {

    // create detail bill( bill, detail_product )
    List<DetailBill> createDetailBill(Bill bill, List<DetailProduct> listProduct);


}
