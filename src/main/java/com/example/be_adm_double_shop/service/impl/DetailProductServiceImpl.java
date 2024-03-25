package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.entity.DetailProduct;
import com.example.be_adm_double_shop.repository.DetailProductRepository;
import com.example.be_adm_double_shop.service.DetailProductService;
import com.example.be_adm_double_shop.util.DateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DetailProductServiceImpl implements DetailProductService {

    @Autowired
    private DetailProductRepository detailProductRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Object getListDetailProductByProductId(Long id) {
        return detailProductRepository.getAllDetailProduct(id);
    }

    @Override
    public Object updateDetailProduct(DetailProduct detailProduct, String username) {
        detailProduct.setUpdatedBy(username);
        detailProduct.setUpdatedTime(DateUtil.dateToString4(new Date()));
        return detailProductRepository.save(detailProduct);
    }

    @Override
    public DetailProduct getOneById(Long id) {
        return detailProductRepository.findById(id).get();
    }

    @Override
    public Object updateAllDetailPro(List<DetailProduct> list) {
        return detailProductRepository.saveAll(list);
    }

}
