package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.entity.Collar;
import com.example.be_adm_double_shop.service.CollarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collar")
@CrossOrigin(origins = { "http://localhost:3000"}, allowCredentials = "true")
public class CollarController {

    @Autowired
    private CollarService collarService;

    @GetMapping("/get-all")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(collarService.getAll());
    }

    @GetMapping("/get-one-by-id/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id){
        return ResponseEntity.ok(collarService.getOneById(id));
    }

    @GetMapping("/get-collar-by-page")
    public ResponseEntity getAllPage(@RequestParam(defaultValue = "0", name = "page") Integer page){
        return new ResponseEntity(collarService.getAllByPage(page).getContent(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Collar collar) {
        return ResponseEntity.ok(collarService.save(collar));
    }

}
