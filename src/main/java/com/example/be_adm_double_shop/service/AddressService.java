package com.example.be_adm_double_shop.service;
import com.example.be_adm_double_shop.dto.request.AddressRequest;
import com.example.be_adm_double_shop.dto.request.CustomerRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Address;
import com.example.be_adm_double_shop.entity.Customer;
import org.springframework.data.domain.Page;

import java.util.List;


public interface AddressService {
    List<Address> getAll(AddressRequest request);

//    Customer getOneId(Long id);
//    Customer delete(Long id );
    Page getAllByPage(int page, int pageSize);

//    Customer save(Customer customer);
//    Customer update(Customer color, Long id);
}
