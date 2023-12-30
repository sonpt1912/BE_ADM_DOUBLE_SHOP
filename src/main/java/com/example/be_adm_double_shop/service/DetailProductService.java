package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.DetailProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DetailProductService {

    Page<DetailProduct> getAllDetailProducts(Pageable pageable);

    DetailProduct getDetailProductById(Long detailProductId);

    DetailProduct createDetailProduct(DetailProduct detailProduct);

    DetailProduct updateDetailProduct(Long detailProductId, DetailProduct updatedDetailProduct);

    DetailProduct deleteDetailProduct(Long detailProductId, DetailProduct updatedDetailProduct);
}
