package com.example.be_adm_double_shop.dto.request;

import com.example.be_adm_double_shop.entity.DetailProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillRequest {

    private Long idCustomer;

    private Long idVoucher;

    private String code;

    private Long totalAmout;

    private Long discoutAmout;

    private String note;

    private Long status;

    private Long payment;

    private List<DetailProduct>listDetailProduct;

//    private String


}
