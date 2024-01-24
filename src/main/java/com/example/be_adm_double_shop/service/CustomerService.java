package com.example.be_adm_double_shop.service;
import com.example.be_adm_double_shop.dto.request.CustomerRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface CustomerService {
    ListResponse<Customer> getAll(CustomerRequest request);

    Customer getOneId(Long id);
    Customer delete(Long id );
    Page getAllByPage(int page, int pageSize);

    Customer save(Customer color);
    Customer update(Customer color, Long id);
}
