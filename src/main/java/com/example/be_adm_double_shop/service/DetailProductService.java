package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.DetailProduct;

public interface DetailProductService {

    Object getListDetailProductByProductId(Long id);

    Object updateDetailProduct(DetailProduct detailProduct);

}
