package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.config.EnableWrapResponse;
import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.entity.Size;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/size")
@EnableWrapResponse
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @Autowired
    private JwtProvider jwtProvider;


    @PostMapping("/get-size-by-condition")
    public ResponseEntity getAllSize(@RequestBody SizeRequest request) {
        return new ResponseEntity(sizeService.getAllByConditon(request), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestHeader("Authorization") String token, @RequestBody Size size) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(sizeService.save(size, "username"), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestHeader("Authorization") String token, @RequestBody Size size) {
        String username = jwtProvider.getUsernameFromToken(token);
        return ResponseEntity.ok(sizeService.update(size, username));
    }

}
