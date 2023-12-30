package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.entity.DetailProduct;
import com.example.be_adm_double_shop.repository.DetailProductRepository;
import com.example.be_adm_double_shop.service.DetailProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class DetailProductServiceImpl implements DetailProductService {

    @Autowired
    private DetailProductRepository detailProductRepository;

    @Override
    public Page<DetailProduct> getAllDetailProducts(Pageable pageable) {
        return detailProductRepository.findAll(pageable);
    }

    @Override
    public DetailProduct getDetailProductById(Long detailProductId) {
        return detailProductRepository.findById(detailProductId).orElse(null);
    }

    @Override
    public DetailProduct createDetailProduct(DetailProduct detailProduct) {
        detailProduct.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        detailProduct.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
        detailProduct.setStatus(1);
        return detailProductRepository.save(detailProduct);
    }

    @Override
    public DetailProduct updateDetailProduct(Long detailProductId, DetailProduct updatedDetailProduct) {
        DetailProduct existingDetailProduct = getDetailProductById(detailProductId);
        if (existingDetailProduct != null) {
            existingDetailProduct.setColor(updatedDetailProduct.getColor());
            existingDetailProduct.setProduct(updatedDetailProduct.getProduct());
            existingDetailProduct.setSize(updatedDetailProduct.getSize());
            existingDetailProduct.setBrand(updatedDetailProduct.getBrand());
            existingDetailProduct.setCategory(updatedDetailProduct.getCategory());
            existingDetailProduct.setCollar(updatedDetailProduct.getCollar());
            existingDetailProduct.setQuantity(updatedDetailProduct.getQuantity());
            existingDetailProduct.setUpdatedBy(updatedDetailProduct.getUpdatedBy());
            existingDetailProduct.setUpdatedTime(new Timestamp(System.currentTimeMillis())); // Cập nhật thời gian ở đây
            return detailProductRepository.save(existingDetailProduct);
        } else {
            return null;
        }
    }

    @Override
    public DetailProduct deleteDetailProduct(Long detailProductId, DetailProduct updatedDetailProduct) {
        DetailProduct existingDetailProduct = getDetailProductById(detailProductId);
        if (existingDetailProduct != null) {
            existingDetailProduct.setStatus(updatedDetailProduct.getStatus());
            existingDetailProduct.setUpdatedBy(updatedDetailProduct.getUpdatedBy());
            existingDetailProduct.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
            return detailProductRepository.save(existingDetailProduct);
        } else {
            return null;
        }
    }
}
