package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.dto.request.SizeRequest;

import com.example.be_adm_double_shop.entity.Collar;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.CollarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/collar")
@CrossOrigin(origins = {"*"})
public class CollarController {
    @Autowired
    private CollarService collarService;

    @Autowired
    private JwtProvider jwtProvider;
    @PostMapping("/get-collar-by-condition")
    public ResponseEntity getAllCollar(@RequestBody SizeRequest request) {
        return new ResponseEntity(collarService.getAll(request), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestHeader("Authorization") String token, @RequestBody Collar collar) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(collarService.save(collar, username), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestHeader("Authorization") String token, @RequestBody Collar collar) {
        String username = jwtProvider.getUsernameFromToken(token);
        return ResponseEntity.ok(collarService.update(collar, username));
    }

    @GetMapping("/get-collar-by-id/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(collarService.getOneId(id));
    }


}
