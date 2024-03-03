package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.dto.request.BrandRequest;
import com.example.be_adm_double_shop.entity.Brand;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/get-size-by-condition")
    public ResponseEntity getAllSize(@RequestBody BrandRequest request) {
        return new ResponseEntity(brandService.getAllByConditon(request), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestHeader("Authorization") String token, @RequestBody Brand brand) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(brandService.save(brand, "username"), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestHeader("Authorization") String token, @RequestBody Brand brand) {
        String username = jwtProvider.getUsernameFromToken(token);
        return ResponseEntity.ok(brandService.update(brand, username));
    }

}
