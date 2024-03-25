package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.DetailProduct;

import java.util.List;

public interface DetailProductService {

    Object getListDetailProductByProductId(Long id);

    Object updateDetailProduct(DetailProduct detailProduct, String username);

    DetailProduct getOneById(Long id);

    Object updateAllDetailPro(List<DetailProduct> list);

}
