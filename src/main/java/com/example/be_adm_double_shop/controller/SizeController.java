package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.entity.Size;
import com.example.be_adm_double_shop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/size")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @GetMapping("/get-all")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(sizeService.getAll());
    }

    @GetMapping("/get-one-by-id/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id){
        return ResponseEntity.ok(sizeService.getOneById(id));
    }

    @GetMapping("/get-size-by-page")
    public ResponseEntity getAllPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize){
        return ResponseEntity.ok(sizeService.getAllByPage(page, pageSize));
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Size size){
        return ResponseEntity.ok(size);
    }

}
