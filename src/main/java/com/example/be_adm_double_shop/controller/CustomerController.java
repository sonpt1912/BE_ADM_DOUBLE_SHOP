package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.entity.Customer;
import com.example.be_adm_double_shop.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/cinema/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/index")
    public ResponseEntity<Page<Customer>> getAllCustomers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        if (page < 1) page = 1;
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<Customer> customerPage = customerService.findAll(pageable);
        return ResponseEntity.ok().body(customerPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") UUID id) {
        Customer customer = customerService.findById(id);
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody @Valid Customer customer) {
        Customer savedCustomer = customerService.save(customer);
        return ResponseEntity.ok().body(savedCustomer);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable("id") UUID id,
            @RequestBody @Valid Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok().body(updatedCustomer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") UUID id) {
        customerService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomers(@RequestParam("keyword") String keyword) {
        List<Customer> customerList = customerService.searchCustomer(keyword);
        return ResponseEntity.ok().body(customerList);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Customer>> getSortedCustomerList() {
        List<Customer> sortedCustomerList = customerService.sapXep();
        return ResponseEntity.ok().body(sortedCustomerList);
    }
}
