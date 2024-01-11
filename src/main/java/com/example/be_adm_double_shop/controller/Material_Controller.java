package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.dto.request.MaterialRequest;
import com.example.be_adm_double_shop.entity.Material;
import com.example.be_adm_double_shop.service.impl.MaterialSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/Material")
@CrossOrigin(origins = { "http://localhost:3000"}, allowCredentials = "true")
public class Material_Controller {
    @Autowired
    private MaterialSer materialSer;

    @RequestMapping("/Material/hien-thi/condition")
    private ResponseEntity show(@RequestBody MaterialRequest request) {
        return new ResponseEntity(materialSer.getAllByCondition(request), HttpStatus.OK);
    }

    @RequestMapping("/Material/hien-thi")
    private List<Material> hienThi() {
        return materialSer.getAll();
    }

    @GetMapping("/Material/hien-thi/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(materialSer.chiTiet(id));
    }

    @RequestMapping("/Material/hien-thi/{viTri}")
    private List<Material> phanTrang(@PathVariable("viTri") Integer viTri ) {
        return materialSer.phanTrang(viTri);
    }

    @RequestMapping("/Material/add")
    private Material add(@RequestBody Material m) {
        return materialSer.add_update(m);
    }

    @PutMapping("/Material/update/{id}")
    private Material update(@RequestBody Material m, @PathVariable("id") Long id) {
        System.out.println(id);
        return materialSer.update(m,id);
    }

    @RequestMapping("/Material/delete/{id}")
    private Material delete(@PathVariable Long id) {
        return materialSer.delete(id);
    }
}
