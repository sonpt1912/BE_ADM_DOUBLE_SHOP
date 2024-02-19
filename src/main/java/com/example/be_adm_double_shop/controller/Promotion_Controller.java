package com.example.be_adm_double_shop.controller;


import com.example.be_adm_double_shop.config.EnableWrapResponse;
import com.example.be_adm_double_shop.dto.request.PromotionRequest;
import com.example.be_adm_double_shop.entity.Promotion;
import com.example.be_adm_double_shop.service.impl.PromotionSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@EnableWrapResponse
@CrossOrigin(origins = { "http://localhost:3000"}, allowCredentials = "true")
public class Promotion_Controller {
    @Autowired
    private PromotionSer promotionSer;

    @RequestMapping("/promotion/hien-thi/condition")
    private ResponseEntity show(@RequestBody PromotionRequest request) {
        return new ResponseEntity(promotionSer.getAllByCondition(request), HttpStatus.OK);
    }

    @RequestMapping("/promotion/hien-thi")
    private List<Promotion> hienThi() {
        return promotionSer.getAll();
    }

    @GetMapping("/promotion/hien-thi/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(promotionSer.getOneById(id));
    }

    @RequestMapping("/promotion/add")
    private List<Promotion> add(@RequestBody Promotion m) {
        promotionSer.add(m);
        return promotionSer.getAll();
    }

    @RequestMapping("/promotion/update/{id}")
    private Promotion update(@RequestBody Promotion m) {
        return promotionSer.update(m);
    }

    @RequestMapping("/promotion/delete/{id}")
    private Promotion delete(@PathVariable Long id) {
        return promotionSer.delete(id);
    }
}
