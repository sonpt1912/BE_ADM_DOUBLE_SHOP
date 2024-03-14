package com.example.be_adm_double_shop.controller;


import com.example.be_adm_double_shop.config.EnableWrapResponse;
import com.example.be_adm_double_shop.dto.request.PromotionRequest;
import com.example.be_adm_double_shop.entity.Address;
import com.example.be_adm_double_shop.entity.Customer;
import com.example.be_adm_double_shop.entity.DetailPromotion;
import com.example.be_adm_double_shop.entity.Promotion;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.impl.PromotionSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@EnableWrapResponse
@CrossOrigin(origins = { "http://localhost:3000"}, allowCredentials = "true")
public class PromotionController {
    @Autowired
    private PromotionSer promotionSer;

    @Autowired
    private JwtProvider jwtProvider;

    @RequestMapping("/promotion/hien-thi/condition")
    private ResponseEntity show(@RequestBody PromotionRequest request) {
        return new ResponseEntity(promotionSer.getAllByCondition(request), HttpStatus.OK);
    }

    @RequestMapping("/promotion/hien-thi")
    private List<Promotion> hienThi() {
        return promotionSer.getAll();
    }

    @RequestMapping("/promotion/hien-thi/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(promotionSer.getOneById(id));
    }

    @RequestMapping("/promotion/show/{idPromotion}/{idDetailPromotion}")
    public ResponseEntity getOne(@PathVariable("idPromotion") Long idPromotion, @PathVariable("idDetailPromotion") Long idDetailPromotion) {
        Promotion promotion = promotionSer.getOneById(idPromotion);
        if (promotion != null) {
            for (DetailPromotion i : promotion.getDetailPromotions()) {
                if (i.getId().equals(idDetailPromotion)) {
                    return ResponseEntity.ok(i);
                }
            }
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/promotion/add")
    private ResponseEntity add(@RequestHeader("Authorization") String token, @RequestBody Promotion m) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(promotionSer.add(m, username), HttpStatus.OK);
    }

    @RequestMapping("/promotion/update/{id}")
    private ResponseEntity update(@RequestHeader("Authorization") String token, @RequestBody Promotion m) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(promotionSer.update(m, username), HttpStatus.OK);
    }

    @RequestMapping("/promotion/delete/{id}")
    private Promotion delete(@PathVariable Long id) {
        return promotionSer.delete(id);
    }
}
