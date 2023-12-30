package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomer();

    Page<Customer> getCustomersByPage(int pageNumber, Pageable pageable);
}
