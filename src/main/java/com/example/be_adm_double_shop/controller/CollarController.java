package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.entity.Collar;
import com.example.be_adm_double_shop.entity.Size;
import com.example.be_adm_double_shop.service.CollarService;
import com.example.be_adm_double_shop.service.SizeService;
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


    @PostMapping("/get-collar-by-condition")
    public ResponseEntity getAllCollar(@RequestBody SizeRequest request) {
        return new ResponseEntity(collarService.getAllByConditon(request), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Collar collar) {
        return new ResponseEntity(collarService.save(collar), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody Collar collar) {
        return ResponseEntity.ok(collarService.update(collar));
    }
}
