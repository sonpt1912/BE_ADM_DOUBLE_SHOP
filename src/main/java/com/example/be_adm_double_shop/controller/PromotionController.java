package com.example.be_adm_double_shop.controller;


import com.example.be_adm_double_shop.config.EnableWrapResponse;
import com.example.be_adm_double_shop.dto.request.PromotionRequest;
import com.example.be_adm_double_shop.entity.DetailPromotion;
import com.example.be_adm_double_shop.entity.Promotion;
import com.example.be_adm_double_shop.repository.ProductRepository;
import com.example.be_adm_double_shop.repository.ProductViewRepository;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.impl.PromotionServicelmpl;
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
    private PromotionServicelmpl promotionSer;

    @Autowired
    private ProductViewRepository productRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/promotion/show/condition")
    private ResponseEntity show(@RequestBody PromotionRequest request) {
        return new ResponseEntity(promotionSer.getAllByCondition(request), HttpStatus.OK);
    }

    @GetMapping("/promotion/show")
    private List<Promotion> hienThi() {
        return promotionSer.getAll();
    }

    @GetMapping("/tree/product/show")
    private ResponseEntity hienThiProduct() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping(value = "/promotion/show/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(promotionSer.getOneById(id));
    }

    @GetMapping("/promotion/show/{idPromotion}/{idDetailPromotion}")
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

    @PostMapping("/promotion/add")
    private ResponseEntity add(@RequestHeader("Authorization") String token, @RequestBody Promotion m) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(promotionSer.add(m, username), HttpStatus.OK);
    }

    @PostMapping("/promotion/update/{id}")
    private ResponseEntity update(@RequestHeader("Authorization") String token, @RequestBody Promotion m) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(promotionSer.update(m, username), HttpStatus.OK);
    }

    @PostMapping("/promotion/delete/{id}")
    private Promotion delete(@PathVariable Long id) {
        return promotionSer.delete(id);
    }
}
