package com.example.be_adm_double_shop.controller;


import com.example.be_adm_double_shop.config.EnableWrapResponse;

import com.example.be_adm_double_shop.dto.request.CustomerRequest;
import com.example.be_adm_double_shop.entity.Address;
import com.example.be_adm_double_shop.entity.Customer;
import com.example.be_adm_double_shop.repository.AdressRepository;
import com.example.be_adm_double_shop.repository.CustomerRepository;
import com.example.be_adm_double_shop.service.CustomerService;
import com.example.be_adm_double_shop.util.DateUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("/customer")

//@EnableWrapResponse
public class CustomerController {

  @Autowired
  private CustomerService customerService;
    @Autowired
    private CustomerRepository a;
    @Autowired
    private AdressRepository adressRepository;
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
        address.setDefaul(1);
        address.setCustomer(customerRequest);

    }
    customerRequest.setAddress(addresses);
    Customer savedCustomer = customerService.save(customerRequest);
    return ResponseEntity.ok(savedCustomer);

}
    @PostMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Customer color, @PathVariable("id") Long id) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.FORMAT_DATE_TIME4);

        String date = simpleDateFormat.format(new Date());
        color.setCreatedTime(date);
                return ResponseEntity.ok(customerService.update(color, id));

    }

    @PostMapping("/add-address/{id}")
    public ResponseEntity addAddressToCustomer(
            @PathVariable("id") Long id,
            @RequestBody Address address
    ) {
        Customer customer = customerService.getOneId(id);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.FORMAT_DATE_TIME4);

        String date = simpleDateFormat.format(new Date());
        if (customer != null) {
            System.out.println(customer.getId());
            if (address.getCity() != null) {
                address.setCustomer(customer);
                address.setCreatedTime(date);
                address.setId(null);
                customer.getAddress().add(address);
                customerService.save(customer);
                return ResponseEntity.ok(customer);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/get-address-by-id/{id_customer}/{id}")
    public ResponseEntity getAddressById(@PathVariable("id_customer") Long customerId, @PathVariable("id") Long addressId) {
        Customer customer = customerService.getOneId(customerId);
        if (customer != null) {
            for (Address address : customer.getAddress()) {
                if (address.getId().equals(addressId)) {
                    return ResponseEntity.ok(address);
                }
            }
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    private Address findAddressById(List<Address> addresses, Long id) {
        for (Address address : addresses) {
            if (address.getId().equals(id)) {
                return address;
            }
        }
        return null;
    }
    @PostMapping("/update-address/{customerId}/{addressId}")
    public ResponseEntity updateAddress(@PathVariable("customerId") Long customerId,
                                        @PathVariable("addressId") Long addressId,
                                        @RequestBody Address updatedAddress) {

        Customer customer = customerService.getOneId(customerId);



            for (Address address : customer.getAddress()) {
                if (address.getId().equals(addressId)) {

                    address.setCity(updatedAddress.getCity());
                    address.setDistrict(updatedAddress.getDistrict());
                    address.setProvince(updatedAddress.getProvince());
                    address.setDescription(updatedAddress.getDescription());


                    address.setDefaul(1);

                } else {

                    address.setDefaul(0);
                }
            }




            return ResponseEntity.ok(customerService.save(customer));

    }


}
