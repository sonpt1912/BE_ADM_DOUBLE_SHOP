package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.entity.Customer;
import com.example.be_adm_double_shop.repository.CustomerRepository;
import com.example.be_adm_double_shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }
    @Override
    public Page<Customer> getCustomersByPage(int pageNumber, Pageable pageable) {
        Pageable pageRequest = PageRequest.of(pageNumber - 1, pageable.getPageSize(), pageable.getSort());
        return customerRepository.findAll(pageRequest);
    }
}
