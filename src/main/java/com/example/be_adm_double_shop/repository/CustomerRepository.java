package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findAllByOrderByUsername();
    List<Customer> findByUsernameContainingIgnoreCase(String keyword);
}