package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.request.ColorRequest;
import com.example.be_adm_double_shop.dto.request.CustomerRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Color;
import com.example.be_adm_double_shop.entity.Customer;
import com.example.be_adm_double_shop.repository.ColorRepository;
import com.example.be_adm_double_shop.repository.CustomerRepository;
import com.example.be_adm_double_shop.service.CustomerService;
import com.example.be_adm_double_shop.util.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository repository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public ListResponse<Customer> getAll(CustomerRequest request) {

        ListResponse listResponse = new ListResponse();

        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        sql.append(" SELECT * FROM customer WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getEmail())) {
            sql.append(" AND EMAIL LIKE CONCAT('%', :email ,'%') ");
            params.put("email", request.getEmail());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getName())) {
            sql.append(" AND NAME LIKE CONCAT('%', :name ,'%') ");
            params.put("name", request.getName());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getPhone())) {
            sql.append(" AND PHONE = :phone ");
            params.put("phone", request.getPhone());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getPageSize())) {
            sql.append(" LIMIT  :page, :size  ");
            if (request.getPageSize() == 0) {
                params.put("page", 0);
            } else {
                params.put("page", (request.getPageSize() * request.getPageSize()));
            }
            params.put("size", request.getPageSize());
        }


        Query query = entityManager.createNativeQuery(sql.toString(), Customer.class);
        params.forEach(query::setParameter);

        listResponse.setListData(query.getResultList());

//
        sql = new StringBuilder();
        params = new HashMap<>();


        sql.append(" SELECT COUNT(*) FROM customer WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getPhone())) {
            sql.append(" AND PHONE LIKE CONCAT('%', :phone ,'%') ");
            params.put("phone", request.getPhone());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getName())) {
            sql.append(" AND NAME LIKE CONCAT('%', :name ,'%') ");
            params.put("name", request.getName());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getEmail())) {
            sql.append(" AND EMAIL = :email ");
            params.put("email", request.getEmail());
        }


        Query queryCount = entityManager.createNativeQuery(sql.toString());
        params.forEach(queryCount::setParameter);

        Integer countData = ((Long) queryCount.getSingleResult()).intValue();

        listResponse.setTotalRecord(countData);
        return listResponse;
    }

    @Override
    public Customer getOneId(Long id) {
        return repository.findById(id).get();
    }



    @Override
    public Page getAllByPage(int page, int pageSize) {
        Pageable p = PageRequest.of(page, pageSize);
        return repository.findAll(p);
    }
    public Customer delete(Long code) {
        // Thực hiện logic xóa ở đây
        Customer cl1 = repository.findById(code).get();

        cl1.setStatus(0);
        return repository.save(cl1);
    }
    @Override
    public Customer save( Customer color) {
        return repository.save(color);
    }

    @Override
    public Customer update(Customer color, Long id) {
        return repository.save(color);
    }


}
