package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.entity.Material;
import com.example.be_adm_double_shop.service.MaterialSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/Material")
@CrossOrigin(origins = { "http://localhost:3000"}, allowCredentials = "true")
public class Material_Controller {
    @Autowired
    private MaterialSer materialSer;

    @RequestMapping("/Material/hien-thi")
    private List<Material> hienThi() {
        return materialSer.getAll();
    }

    @RequestMapping("/Material/hien-thi/{viTri}")
    private List<Material> phanTrang(@PathVariable("viTri") Integer viTri ) {
        return materialSer.phanTrang(viTri);
    }

    @RequestMapping("/Material/add")
    private Material add(@RequestBody Material m) {
        return materialSer.add_update(m);
    }

    @RequestMapping("/Material/update/{id}")
    private Material update(@RequestBody Material m) {
        return materialSer.add_update(m);
    }

    @RequestMapping("/Material/delete/{id}")
    private void delete(@PathVariable Long id) {
        materialSer.delete(id);
    }
}
