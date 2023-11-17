package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.entity.Color;
import com.example.be_adm_double_shop.service.ColorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/color")
@CrossOrigin(origins = { "http://localhost:3000"}, allowCredentials = "true")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(colorService.getAll());
    }

    @GetMapping("/get-one-by-id/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(colorService.getOneById(id));
    }

    @GetMapping("/get-color-by-page")
    public ResponseEntity getAllPage(@RequestParam(defaultValue = "0", name = "page") Integer page) {
        return new ResponseEntity(colorService.getAllByPage(page).getContent(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody @Valid Color color) {
        return ResponseEntity.ok(colorService.save(color));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Color color){
        return ResponseEntity.ok(colorService.update(color, id));
    }

}
