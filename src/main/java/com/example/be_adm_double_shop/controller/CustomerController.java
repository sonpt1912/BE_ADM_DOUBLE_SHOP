package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.dto.request.ColorRequest;
import com.example.be_adm_double_shop.dto.request.CustomerRequest;
import com.example.be_adm_double_shop.entity.Customer;
import com.example.be_adm_double_shop.service.CustomerService;
import com.example.be_adm_double_shop.service.impl.ColorServiceImpl;
import com.example.be_adm_double_shop.util.DateUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = { "http://localhost:3000"}, allowCredentials = "true")
public class CustomerController {

  @Autowired
  private CustomerService customerService;
    @PostMapping("/get-all")
    public ResponseEntity getAllColor(@RequestBody CustomerRequest request) {
        return new ResponseEntity(customerService.getAll(request), HttpStatus.OK);
    }
    @GetMapping("/get-one-by-id/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.getOneId(id));
    }
    @PostMapping("/delete/{code}")
    public Customer delete(@PathVariable("code") Long code){
        return  customerService.delete(code);
    }

    @PostMapping("/save")
    public ResponseEntity save( @Valid @RequestBody Customer color ) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.FORMAT_DATE_TIME4);
        String date = simpleDateFormat.format(new Date());
        color.setCreatedTime(date);


        return ResponseEntity.ok(customerService.save(color));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Customer color, @PathVariable("id") Long id) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.FORMAT_DATE_TIME4);
        String date = simpleDateFormat.format(new Date());
        color.setUpdatedTime(date);
        color.setCreatedTime(date);
        return ResponseEntity.ok(customerService.update(color, id));
    }
}
