package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.entity.Category;
import com.example.be_adm_double_shop.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get-all")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/get-one-by-id/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id){
        return ResponseEntity.ok(categoryService.getOneById(id));
    }

    @GetMapping("/get-category-by-page")
    public ResponseEntity getAllPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize){
        return ResponseEntity.ok(categoryService.getAllByPage(page, pageSize));
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.save(category));
    }

}
