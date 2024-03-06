package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.config.EnableWrapResponse;
import com.example.be_adm_double_shop.dto.request.AddressRequest;
import com.example.be_adm_double_shop.dto.request.CustomerRequest;
import com.example.be_adm_double_shop.entity.Address;
import com.example.be_adm_double_shop.entity.Customer;
import com.example.be_adm_double_shop.repository.AdressRepository;
import com.example.be_adm_double_shop.service.AddressService;
import com.example.be_adm_double_shop.service.CustomerService;
import com.example.be_adm_double_shop.util.DateUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/address")


public class AdressController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private AdressRepository adressRepository;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/get-all")
    public ResponseEntity getAllColor(@RequestBody AddressRequest request) {

//      System.out.println("a" +  customerService.getAll(request).);

        return new ResponseEntity(addressService.getAll(request), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Address updatedAddress, @PathVariable("id") Long id) {
        Address address = addressService.getOneId(id);

        if (address != null) {
            address.setCity(updatedAddress.getCity());
            address.setDistrict(updatedAddress.getDistrict());
            address.setProvince(updatedAddress.getProvince());
            address.setDescription(updatedAddress.getDescription());

//            Address updatedAddress = addressService.save(address);
            return ResponseEntity.ok(addressService.save(address));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}