package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.request.CustomerRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Customer;
import com.example.be_adm_double_shop.repository.AdressRepository;
import com.example.be_adm_double_shop.repository.CustomerRepository;
import com.example.be_adm_double_shop.repository.RankRepository;
import com.example.be_adm_double_shop.service.CustomerService;
import com.example.be_adm_double_shop.util.Constant;
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
    @Autowired
    private RankRepository rankRepository;
    @Autowired
    private AdressRepository a;

    @PersistenceContext
    private EntityManager entityManager1;


    @Override
    public ListResponse<Customer> getAll(CustomerRequest request) {

        ListResponse listResponse = new ListResponse();

        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();


        sql.append(" SELECT * FROM customer WHERE 1 = 1 ");
        if (!StringUtil.stringIsNullOrEmty(request.getPhone())) {
            sql.append(" AND phone LIKE CONCAT('%', :phone ,'%') ");
            params.put("phone", request.getPhone());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getStatus())) {
            sql.append(" AND status = :status ");
            params.put("status", request.getStatus());
        }



//        if (!StringUtil.stringIsNullOrEmty(request.getPageSize())) {
//            sql.append(" LIMIT  :page, :size  ");
//            if (request.getPageSize() == 0) {
//                params.put("page", 0);
//            } else {
//                params.put("page", (request.getPageSize() * request.getPageSize()));
//            }
//            params.put("size", request.getPageSize());
//        }


        Query query = entityManager1.createNativeQuery(sql.toString(), Customer.class);
        params.forEach(query::setParameter);

        listResponse.setListData(query.getResultList());

        sql = new StringBuilder();
        params = new HashMap<>();


        sql.append("SELECT COUNT(*) FROM customer WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getPhone())) {
            sql.append(" AND phone LIKE CONCAT('%', :phone ,'%') ");
            params.put("phone", request.getPhone());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getStatus())) {
            sql.append(" AND status = :status ");
            params.put("status", request.getStatus());
        }


        Query queryCount = entityManager1.createNativeQuery(sql.toString());
        params.forEach(queryCount::setParameter);

        Integer countData = ((Long) queryCount.getSingleResult()).intValue();

        listResponse.setTotalRecord(countData);

        return listResponse;
    }

    @Override
    public Customer getOneId(Long id) {
        return repository.findById(id).orElse(null);
    }



    @Override
    public Page getAllByPage(int page, int pageSize) {
        Pageable p = PageRequest.of(page, pageSize);
        return repository.findAll(p);
    }
    public Customer delete(Long id) {
        System.out.println(id);
        Customer cl1 = repository.findById(id).get();
        cl1.setStatus(Integer.valueOf(Constant.IN_ACTIVE));
        return repository.save(cl1);
    }

    @Override
    public Customer save( Customer color) {
        color.setRank(rankRepository.findById(Long.valueOf(1)).get());

        return repository.save(color);
    }



    @Override
    public Customer update(Customer customer, Long id) {
        customer.setRank(rankRepository.findById(Long.valueOf(1)).get());
        customer.setId(id);
        return repository.save(customer);
    }


}
