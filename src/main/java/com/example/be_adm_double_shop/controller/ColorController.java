package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.dto.request.ColorRequest;
import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.entity.Color;
import com.example.be_adm_double_shop.repository.ColorRepository;
import com.example.be_adm_double_shop.service.ColorService;
import com.example.be_adm_double_shop.service.impl.ColorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping("/color")
@CrossOrigin(origins = { "http://localhost:3000"}, allowCredentials = "true")
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
        System.out.println("code"+ id);
//        System.out.println("ten" + colorService.getOneById(id).getName());
        return ResponseEntity.ok(colorService.getOneId(id));
    }
    @DeleteMapping("/delete/{code}")
    public Color delete(@PathVariable("code") Long code){
        System.out.println("code xoa" + code);
        return  colorService.delete(code);
//        return new ResponseEntity<>("Color deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/get-color-by-page")
    public ResponseEntity getAllPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        return ResponseEntity.ok(colorService.getAllByPage(page, pageSize));
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Color color) {
        System.out.println("color" + color.getCreatedTime());
        String pattern = "yyyy-MM-dd hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);
        color.setCreatedTime(date);

        return ResponseEntity.ok(colorService.save(color));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Color color, @PathVariable("id") Long id) {
        System.out.println("color" + color.getCreatedTime());
        String pattern = "yyyy-MM-dd hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);
        color.setUpdatedTime(date);
        color.setCreatedTime(date);
        System.out.println("idd"+ id);
        return ResponseEntity.ok(colorService.update(color, id));
    }
}
