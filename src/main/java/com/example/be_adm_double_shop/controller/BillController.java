package com.example.be_adm_double_shop.controller;


import com.example.be_adm_double_shop.dto.request.BillRequest;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private JwtProvider jwtProvider;

    // add new bill
    @PostMapping("/create-bill")
    public ResponseEntity createBill(@RequestBody BillRequest billRequest, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String creator = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(billService.createBill(billRequest, creator), HttpStatus.OK);
    }

    @PostMapping("/update-bill")
    public ResponseEntity updateBill(@RequestBody BillRequest billRequest, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(billService.updateBill(billRequest, username), HttpStatus.OK);
    }   

}
