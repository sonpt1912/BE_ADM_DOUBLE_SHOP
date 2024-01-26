package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.entity.Collar;
import com.example.be_adm_double_shop.entity.Promotion;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.CollarService;
import com.example.be_adm_double_shop.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promotion")
@CrossOrigin(origins = {"*"})
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @Autowired
    private JwtProvider jwtProvider;
    @PostMapping("/get-promotion-by-condition")
    public ResponseEntity getAllPromotion(@RequestBody SizeRequest request) {
        return new ResponseEntity(promotionService.getAll(request), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestHeader("Authorization") String token, @RequestBody Promotion promotion) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(promotionService.save(promotion, username), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestHeader("Authorization") String token, @RequestBody Promotion promotion) {
        String username = jwtProvider.getUsernameFromToken(token);
        return ResponseEntity.ok(promotionService.update(promotion, username));
    }

    @GetMapping("/get-promotion-by-id/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(promotionService.getOneId(id));
    }
    @PostMapping("/delete/{id}")
    public Promotion delete(@PathVariable("id") Long id){
        return promotionService.delete(id);
    }

}
