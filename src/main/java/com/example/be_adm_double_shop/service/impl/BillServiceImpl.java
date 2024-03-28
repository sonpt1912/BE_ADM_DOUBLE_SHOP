package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.ValidationException;
import com.example.be_adm_double_shop.dto.request.BillRequest;
import com.example.be_adm_double_shop.entity.*;
import com.example.be_adm_double_shop.repository.BillRepository;
import com.example.be_adm_double_shop.service.BillHistoryService;
import com.example.be_adm_double_shop.service.BillService;
import com.example.be_adm_double_shop.service.DetailBillService;
import com.example.be_adm_double_shop.service.DetailProductService;
import com.example.be_adm_double_shop.util.Constant;
import com.example.be_adm_double_shop.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private BillHistoryService billHistoryService;

    @Override
    public Object createBill(BillRequest billRequest, String creator) {
        String code = UUID.randomUUID().toString();
        Boolean check = true;
        while (check) {
            code = UUID.randomUUID().toString();
            check = billRepository.existsByCode(code);
        }

        // create bill
        Bill bill = billRepository.save(Bill.builder()
                .code(code)
                .totalAmount(billRequest.getTotalAmout())
                .customer(Customer.builder().id(billRequest.getIdCustomer()).build())
                .voucher(Voucher.builder().id(billRequest.getIdVoucher()).build())
//                .employee(Employee.builder().build())
                .discountAmount(billRequest.getDiscoutAmout())
                .orderDate(DateUtil.dateToString4(new Date()))
                .status(Constant.ACTIVE)
                .build());

        if (bill != null) {
            // create bill_history
            BillHistory billHistory = BillHistory.builder()
                    .bill(bill)
                    .createdBy(creator)
                    .description("Tạo hóa đơn, thanh toán bán hàng tại quầy")
                    .build();
            billHistoryService.createBillHistory(billHistory);

            // add cac san pham vao bill
            List<DetailBill> dbl = detailBillService.createDetailBill(bill, billRequest.getListDetailProduct());
            if (dbl != null) {
                // update cac gia tri cua detial product
                for (DetailProduct dt : billRequest.getListDetailProduct()) {
                    DetailProduct currentProduct = detailProductService.getOneById(dt.getId());
                    long quantity = currentProduct.getQuantity() - dt.getQuantity();
                    currentProduct.setQuantity(quantity);
                    if (quantity == 0) {
                        currentProduct.setStatus(Constant.IN_ACTIVE);
                    }
                    detailProductService.updateDetailProduct(currentProduct, creator);
                }


            }
            return Constant.SUCCESS;
        }
        throw new ValidationException(Constant.API001, "");
    }

    @Override
    public Object updateBill(BillRequest billRequest, String username) {
        return null;
    }

    @Override
    public Object getAllByCondition(BillRequest billRequest) {
        return null;
    }
}
