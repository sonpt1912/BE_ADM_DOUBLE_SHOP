package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.dto.ColorRequest;
import com.example.be_adm_double_shop.service.ColorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/color")
@CrossOrigin(origins = { "http://localhost:3000"}, allowCredentials = "true")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(colorService.list());
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
    public ResponseEntity save(@RequestBody @Valid ColorRequest colorRequest, BindingResult result) {
        if(result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.ok(colorService.save(colorRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return ResponseEntity.ok(colorService.delete(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Long id, ColorRequest colorRequest, BindingResult result){
        if(result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.ok(colorService.update(colorRequest, id));
    }

}
