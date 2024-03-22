package com.example.be_adm_double_shop.controller;


import com.example.be_adm_double_shop.config.EnableWrapResponse;
import com.example.be_adm_double_shop.dto.request.DetailPromotionRequest;
import com.example.be_adm_double_shop.dto.request.PromotionRequest;
import com.example.be_adm_double_shop.entity.DetailProduct;
import com.example.be_adm_double_shop.entity.DetailPromotion;
import com.example.be_adm_double_shop.repository.DetailProductRepository;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.impl.DetailPromotionServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@EnableWrapResponse
@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true")
public class DetailPromotionController {
    @Autowired
    private DetailPromotionServicelmpl detailPromotionServicelmpl;

    @Autowired
    private DetailProductRepository detailProductRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @RequestMapping("/detail-promotion/hien-thi/condition")
    private ResponseEntity show(@RequestBody DetailPromotionRequest request) {
        return new ResponseEntity(detailPromotionServicelmpl.getAllByCondition(request), HttpStatus.OK);
    }

    @RequestMapping("/detail-product/show")
    private List<DetailProduct> showProduct() {
        return detailProductRepository.findAll();
    }

    @RequestMapping("/detail-promotion/hien-thi")
    private List<DetailPromotion> hienThi() {
        return detailPromotionServicelmpl.getAll();
    }

    @RequestMapping("/detail-promotion/hien-thi/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(detailPromotionServicelmpl.getOneById(id));
    }

    @RequestMapping("/detail-promotion/add")
    private ResponseEntity add(@RequestHeader("Authorization") String token, @RequestBody PromotionRequest m) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(detailPromotionServicelmpl.add(m, username), HttpStatus.OK);
    }

//    @RequestMapping("/detail-promotion/add")
//    private ResponseEntity add(@RequestHeader("Authorization") String token, @RequestBody Promotion m, @RequestBody DetailPromotion p) {
//        String username = jwtProvider.getUsernameFromToken(token);
//        return new ResponseEntity(detailPromotionServicelmpl.add(m, p, username), HttpStatus.OK);
//    }

    @RequestMapping("/detail-promotion/update/{id}")
    private ResponseEntity update(@RequestHeader("Authorization") String token, @RequestBody PromotionRequest p) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(detailPromotionServicelmpl.update(p, username), HttpStatus.OK);
    }

    @RequestMapping("/detail-promotion/delete/{id}")
    private DetailPromotion delete(@PathVariable Long id) {
        return detailPromotionServicelmpl.delete(id);
    }
}
