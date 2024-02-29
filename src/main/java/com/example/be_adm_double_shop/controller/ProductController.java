package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.repository.DetailProductRepository;
import com.example.be_adm_double_shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DetailProductRepository detailProductRepository;

    @PostMapping("/get-all-product")
    public ResponseEntity getAllProduct() {
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PostMapping("/get-detail-product")
    public ResponseEntity getDetailProduct() {
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PostMapping("/create-product")
    public ResponseEntity createProduct() {
        return new ResponseEntity(null, HttpStatus.OK);
    }

}