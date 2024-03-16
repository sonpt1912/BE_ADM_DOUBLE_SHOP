package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.dto.request.BrandRequest;
import com.example.be_adm_double_shop.dto.request.CategoryRequest;
import com.example.be_adm_double_shop.entity.Brand;
import com.example.be_adm_double_shop.entity.Category;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/get-category-by-condition")
    public ResponseEntity getAllCategory(@RequestBody CategoryRequest request) {
        return new ResponseEntity(categoryService.getAllByConditon(request), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestHeader("Authorization") String token, @RequestBody Category category) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(categoryService.save(category, "username"), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestHeader("Authorization") String token, @RequestBody Category category) {
        String username = jwtProvider.getUsernameFromToken(token);
        return ResponseEntity.ok(categoryService.update(category, username));
    }


}
