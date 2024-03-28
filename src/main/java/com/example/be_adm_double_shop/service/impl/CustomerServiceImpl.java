package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.request.CustomerRequest;
import com.example.be_adm_double_shop.dto.request.MailRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Address;
import com.example.be_adm_double_shop.entity.Customer;
import com.example.be_adm_double_shop.repository.AdressRepository;
import com.example.be_adm_double_shop.repository.CustomerRepository;
import com.example.be_adm_double_shop.service.CustomerService;
import com.example.be_adm_double_shop.service.MailService;
import com.example.be_adm_double_shop.util.Constant;
import com.example.be_adm_double_shop.util.DateUtil;
import com.example.be_adm_double_shop.util.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private AdressRepository a;

    @Autowired
    private MailService mailService;

    @PersistenceContext
    private EntityManager entityManager1;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        if (!StringUtil.stringIsNullOrEmty(request.getUsername())) {
            sql.append(" AND username LIKE CONCAT('%', :username ,'%') ");
            params.put("username", request.getUsername());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getEmail())) {
            sql.append(" AND email LIKE CONCAT('%', :email ,'%')");
            params.put("email", request.getEmail());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getName())) {
            sql.append(" AND name LIKE CONCAT('%',  :name , '%')");
            params.put("name", request.getName());
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
        if (!StringUtil.stringIsNullOrEmty(request.getUsername())) {
            sql.append(" AND username LIKE CONCAT('%', :username ,'%') ");
            params.put("username", request.getUsername());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getEmail())) {
            sql.append(" AND email LIKE CONCAT('%', :email ,'%')");
            params.put("email", request.getEmail());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getName())) {
            sql.append(" AND name LIKE CONCAT('%',  :name , '%')");
            params.put("name", request.getName());
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

    @Override
    public List<Customer> findByPhone(String phone) {
        return repository.findByPhone(phone);
    }


    public Customer delete(Long id) {
        System.out.println(id);
        Customer cl1 = repository.findById(id).get();
        cl1.setStatus(Integer.valueOf(Constant.IN_ACTIVE));
        return repository.save(cl1);
    }

    @Override
    public Object createCustomer(Customer color, String a) {
//        color.setRank(rankRepository.findById(Long.valueOf(1)).get());
        color.setCreatedBy("huong");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.FORMAT_DATE_TIME4);
        String date = simpleDateFormat.format(new Date());
        color.setCreatedTime(date);
        List<Address> addresses = color.getAddress();
        for (Address address : addresses) {
            address.setCreatedBy("huong");
            address.setCreatedTime(date);
            address.setDefaul(1);
            address.setCustomer(color);
        }
        color.setAddress(addresses);
        String password = Constant.DEFAULT_PASSWORD;
        if (!StringUtil.stringIsNullOrEmty(color.getPassword())) {
            password = color.getPassword();
        }
        color.setPassword(passwordEncoder.encode(password));
        try {
            repository.save(color);
            MailRequest mailRequest = MailRequest.builder()
                    .reciver(color.getEmail())
                    .content(password)
                    .subContent(color.getUsername())
                    .build();
            mailService.sendMailCreateAccount(mailRequest);
            return Constant.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
//         repository.save(color);
    }


    @Override
    public Customer update(Customer customer, Long id) {
//        customer.setRank(rankRepository.findById(Long.valueOf(1)).get());
        customer.setId(id);
        return repository.save(customer);
    }


}
