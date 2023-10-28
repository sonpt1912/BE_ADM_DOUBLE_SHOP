package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.entity.Material;
import com.example.be_adm_double_shop.service.MaterialService;
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
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping("/get-all")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(materialService.getAll());
    }

    @GetMapping("/get-one-by-id/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id){
        return ResponseEntity.ok(materialService.getOneById(id));
    }

    @GetMapping("/get-material-by-page")
    public ResponseEntity getAllPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize){
        return ResponseEntity.ok(materialService.getAllByPage(page, pageSize));
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Material material){
        return ResponseEntity.ok(materialService.save(material));
    }

}
