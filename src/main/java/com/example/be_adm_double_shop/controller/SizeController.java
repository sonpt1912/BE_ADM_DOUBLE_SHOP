package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.entity.Size;
import com.example.be_adm_double_shop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/size")
@CrossOrigin(origins = { "http://localhost:3000"}, allowCredentials = "true")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @GetMapping("/get-all")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(sizeService.getAll());
    }

    @GetMapping("/get-one-by-id/{id}")
    public ResponseEntity getOneById(@PathVariable("id") String id){
        return ResponseEntity.ok(sizeService.getOneById(Long.valueOf(id)));
    }

    @GetMapping("/get-size-by-page")
    public ResponseEntity getAllPage(@RequestParam(defaultValue = "0", name = "page") Integer page){
        return new ResponseEntity(sizeService.getAllByPage(page).getContent(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Size size) {
        return ResponseEntity.ok(sizeService.save(size));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Size size){
        return ResponseEntity.ok(sizeService.update(size, id));
    }
}
