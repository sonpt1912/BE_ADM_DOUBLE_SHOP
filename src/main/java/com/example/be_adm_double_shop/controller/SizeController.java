package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/size")
@CrossOrigin(origins = {"*"})
public class SizeController {

    @Autowired
    private SizeService sizeService;


    @PostMapping("/get-size-by-condition")
    private ResponseEntity getAllSize(@RequestBody SizeRequest request) {
        return new ResponseEntity(sizeService.getAllbByConditon(request), HttpStatus.OK);
    }

}
