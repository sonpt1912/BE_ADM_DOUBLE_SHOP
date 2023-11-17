package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.entity.Product;
import com.example.be_adm_double_shop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = { "http://localhost:3000"}, allowCredentials = "true")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/get-one-by-id/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getOneById(id));
    }

    @GetMapping("/get-product-by-page")
    public ResponseEntity getAllPage(@RequestParam(defaultValue = "0", name = "page") Integer page) {
        return new ResponseEntity(productService.getAllByPage(page).getContent(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody @Valid Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Product product){
        return ResponseEntity.ok(productService.update(product, id));
    }

}
