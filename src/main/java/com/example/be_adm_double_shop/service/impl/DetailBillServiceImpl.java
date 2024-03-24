package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.request.DetailProductRequest;
import com.example.be_adm_double_shop.entity.Bill;
import com.example.be_adm_double_shop.entity.DetailBill;
import com.example.be_adm_double_shop.entity.DetailProduct;
import com.example.be_adm_double_shop.repository.DetailBillRepository;
import com.example.be_adm_double_shop.service.DetailBillService;
import com.example.be_adm_double_shop.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailBillServiceImpl implements DetailBillService {

    @Autowired
    private DetailBillRepository detailBillRepository;

    @Override
    public List<DetailBill> createDetailBill(Bill bill, List<DetailProduct> listProduct) {
        List<DetailBill> listDetailBill = new ArrayList<>();
        for (DetailProduct detailProduct : listProduct) {
            DetailBill detailBill = DetailBill.builder()
                    .bill(bill)
                    .quantity(detailProduct.getQuantity())
                    .price(detailProduct.getPrice())
                    .detailProduct(DetailProduct.builder().id(detailProduct.getId()).build())
                    .status(Constant.ACTIVE.intValue())
                    .build();
            listDetailBill.add(detailBill);
        }
        return detailBillRepository.saveAll(listDetailBill);
    }
}
