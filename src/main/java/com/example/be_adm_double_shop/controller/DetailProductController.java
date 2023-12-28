package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.entity.DetailProduct;
import com.example.be_adm_double_shop.service.DetailProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/detailProduct")
@CrossOrigin(origins = { "http://localhost:3000"}, allowCredentials = "true")

public class DetailProductController {

    @Autowired
    private DetailProductService detailProductService;

    @GetMapping("/get-all")
    public ResponseEntity<Page<DetailProduct>> getAllDetailProducts(
            @RequestParam int page,
            @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<DetailProduct> detailProducts = detailProductService.getAllDetailProducts(pageable);
        return ResponseEntity.ok(detailProducts);
    }

    @GetMapping("/get-one-by-id/{id}")
    public ResponseEntity<DetailProduct> getDetailProductById(@PathVariable Long id) {
        DetailProduct detailProduct = detailProductService.getDetailProductById(id);
        return ResponseEntity.ok(detailProduct);
    }
    @PostMapping("/save")
    public ResponseEntity<DetailProduct> createDetailProduct(@RequestBody DetailProduct detailProduct) {
        DetailProduct createdDetailProduct = detailProductService.createDetailProduct(detailProduct);
        return ResponseEntity.ok(createdDetailProduct);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DetailProduct> updateDetailProduct(@PathVariable Long id, @RequestBody DetailProduct updatedDetailProduct) {
        DetailProduct detailProduct = detailProductService.updateDetailProduct(id, updatedDetailProduct);
        return ResponseEntity.ok(detailProduct);
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDetailProduct(@PathVariable Long id,@RequestBody DetailProduct updatedDetailProduct) {
        DetailProduct detailProduct = detailProductService.deleteDetailProduct(id, updatedDetailProduct);
        return ResponseEntity.ok().build();
    }


}
