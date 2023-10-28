package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.entity.Promotion;
import com.example.be_adm_double_shop.service.PromotionService;
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
@RequestMapping("/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/get-all")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(promotionService.getAll());
    }

    @GetMapping("/get-one-by-id/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id){
        return ResponseEntity.ok(promotionService.getOneById(id));
    }

    @GetMapping("get-color-by-page")
    public ResponseEntity getAllPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize){
        return ResponseEntity.ok(promotionService.getAllByPage(page, pageSize));
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Promotion promotion){
        return ResponseEntity.ok(promotionService.save(promotion));
    }

}
