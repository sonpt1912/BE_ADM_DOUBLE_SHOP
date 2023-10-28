package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.entity.Brand;
import com.example.be_adm_double_shop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/get-all")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(brandService.getAll());
    }

    @GetMapping("/get-one-by-id/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id){
        return ResponseEntity.ok(brandService.getOneById(id));
    }

    @GetMapping("/get-brand-by-page")
    public ResponseEntity getAllPage(@RequestParam(defaultValue = "0", name = "page") int page) {
        return ResponseEntity.ok(brandService.getAllByPage(page));
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Brand brand){
        return ResponseEntity.ok(brandService.save(brand));
    }

}
