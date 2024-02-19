package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.config.EnableWrapResponse;
import com.example.be_adm_double_shop.dto.request.ColorRequest;
import com.example.be_adm_double_shop.dto.request.CustomerRequest;
import com.example.be_adm_double_shop.entity.Address;
import com.example.be_adm_double_shop.entity.Customer;
import com.example.be_adm_double_shop.repository.CustomerRepository;
import com.example.be_adm_double_shop.service.CustomerService;
import com.example.be_adm_double_shop.service.impl.ColorServiceImpl;
import com.example.be_adm_double_shop.util.DateUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/customer")
@EnableWrapResponse
@CrossOrigin(origins = { "http://localhost:3000"}, allowCredentials = "true")
public class CustomerController {

  @Autowired
  private CustomerService customerService;
    @Autowired
    private CustomerRepository a;
    @PostMapping("/get-all")
    public ResponseEntity getAll(@RequestBody CustomerRequest request) {

        return new ResponseEntity(customerService.getAll(request), HttpStatus.OK);
    }
    @GetMapping("/get-one-by-id/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(customerService.getOneId(id));
    }
    @PostMapping("/delete/{id}")
    public Customer delete(@PathVariable("id") Long id){
        System.out.println("status" +id);
        return  customerService.delete(id);
    }




@PostMapping("/save")
public ResponseEntity save(@Valid @RequestBody Customer customerRequest) {
    customerRequest.setCreatedBy("huong");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.FORMAT_DATE_TIME4);
    String date = simpleDateFormat.format(new Date());
    customerRequest.setCreatedTime(date);

    List<Address> addresses = customerRequest.getAddress();
    for (Address address : addresses) {
        address.setCreatedBy("huong");
        address.setCreatedTime(date);
        address.setCustomer(customerRequest);
    }
    customerRequest.setAddress(addresses);
    Customer savedCustomer = customerService.save(customerRequest);
    return ResponseEntity.ok(savedCustomer);

}

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Customer color, @PathVariable("id") Long id) {

        return ResponseEntity.ok(customerService.update(color, id));
    }
}
