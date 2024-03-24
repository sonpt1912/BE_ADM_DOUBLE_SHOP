package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.ValidationException;
import com.example.be_adm_double_shop.dto.request.BillRequest;
import com.example.be_adm_double_shop.entity.*;
import com.example.be_adm_double_shop.repository.BillRepository;
import com.example.be_adm_double_shop.service.BillService;
import com.example.be_adm_double_shop.service.DetailBillService;
import com.example.be_adm_double_shop.service.DetailProductService;
import com.example.be_adm_double_shop.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private DetailBillService detailBillService;

    @Autowired
    private DetailProductService detailProductService;

    @Override
    public Object createBill(BillRequest billRequest, String creator) {
        String code = UUID.randomUUID().toString();
        Boolean check = true;
        while (check) {
            code = UUID.randomUUID().toString();
            check = billRepository.existsByCode(code);
        }

        Bill bill = billRepository.save(Bill.builder()
                .code(code)
                .totalAmount(billRequest.getTotalAmout())
//                .customer(Customer.builder().id(billRequest.getIdCustomer()).build())
//                .voucher(Voucher.builder().id(billRequest.getIdVoucher()).build())
//                .employee(Employee.builder().build())
                .status(Constant.ACTIVE)
                .build());
        if (bill != null) {
            // add cac san pham vao bill
            List<DetailBill> dbl = detailBillService.createDetailBill(bill, billRequest.getListDetailProduct());
            if (dbl != null) {
                // update cac gia tri cua detial product
                List<DetailProduct> listDP = new ArrayList<>();
                for (DetailProduct dt : billRequest.getListDetailProduct()) {
                    DetailProduct currentProduct = detailProductService.getOneById(dt.getId());
                    dt = DetailProduct.builder()
                            .quantity(currentProduct.getQuantity() - dt.getQuantity())
                            .build();
                    listDP.add(dt);
                }
            }
            return Constant.SUCCESS;
        }
        throw new ValidationException(Constant.API001, "");
    }

    @Override
    public Object updateBill(BillRequest billRequest, String creator) {
        return null;
    }
}
