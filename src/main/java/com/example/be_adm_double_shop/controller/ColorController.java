package com.example.be_adm_double_shop.controller;


import com.example.be_adm_double_shop.dto.request.ColorRequest;


import com.example.be_adm_double_shop.config.EnableWrapResponse;
import com.example.be_adm_double_shop.entity.Color;
import com.example.be_adm_double_shop.service.ColorService;
import com.example.be_adm_double_shop.service.impl.ColorServiceImpl;
import com.example.be_adm_double_shop.util.DateUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/color")
@EnableWrapResponse
public class ColorController {

    @Autowired
    private ColorService colorService;
    @Autowired
    private ColorServiceImpl repository;
    @PostMapping("/get-all")
    public ResponseEntity getAllColor(@RequestBody ColorRequest request) {
        return new ResponseEntity(colorService.getAll(request), HttpStatus.OK);
    }
    @GetMapping("/get-one-by-id/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(colorService.getOneId(id));
    }
    @PostMapping("/delete/{code}")
    public Color delete(@PathVariable("code") Long code){
        return  colorService.delete(code);
    }

    @PostMapping("/save")
    public ResponseEntity save( @Valid @RequestBody Color color ) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.FORMAT_DATE_TIME4);
        String date = simpleDateFormat.format(new Date());
        color.setCreatedTime(date);
        return ResponseEntity.ok(colorService.save(color));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Color color, @PathVariable("id") Long id) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.FORMAT_DATE_TIME4);
        String date = simpleDateFormat.format(new Date());
        color.setUpdatedTime(date);
        color.setCreatedTime(date);
        return ResponseEntity.ok(colorService.update(color, id));
    }
}
