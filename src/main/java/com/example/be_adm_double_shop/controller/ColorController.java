package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.entity.Color;
import com.example.be_adm_double_shop.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/color")
@CrossOrigin(origins = {"*"})
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
    public ResponseEntity getAllPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        return ResponseEntity.ok(colorService.getAllByPage(page, pageSize));
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Color color) {
        return ResponseEntity.ok(colorService.save(color));
    }

}
