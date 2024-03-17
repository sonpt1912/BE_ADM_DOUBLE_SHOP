package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
   List<Customer> findByPhone(String phone);
}
