package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.entity.Customer;
import com.example.be_adm_double_shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = { "http://localhost:3000"}, allowCredentials = "true")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Customer>> getAllCustomers(@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber, Pageable pageable) {
        Page<Customer> customers = customerService.getCustomersByPage(pageNumber, pageable);
        return ResponseEntity.ok(customers);
    }


}
