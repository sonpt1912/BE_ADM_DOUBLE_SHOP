package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.dto.request.VoucherRequest;
import com.example.be_adm_double_shop.entity.Voucher;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/voucher")
@CrossOrigin(origins = {"*"})
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @Autowired
    private JwtProvider jwtProvider;
    @PostMapping("/get-voucher-by-condition")
    public ResponseEntity getAllVoucher(@RequestBody VoucherRequest request) {
        return new ResponseEntity(voucherService.getAll(request), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestHeader("Authorization") String token, @RequestBody Voucher voucher) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(voucherService.save(voucher, username), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestHeader("Authorization") String token, @RequestBody Voucher voucher) throws ParseException {
        String username = jwtProvider.getUsernameFromToken(token);
        return ResponseEntity.ok(voucherService.update(voucher, username));
    }

    @GetMapping("/get-voucher-by-id/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(voucherService.getOneId(id));
    }

}
