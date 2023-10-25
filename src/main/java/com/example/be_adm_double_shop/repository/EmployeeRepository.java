package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    List<Employee> findByCodeContainingIgnoreCaseOrNameContainingIgnoreCase(String code, String name);

    List<Employee> findAllByOrderByCode();
}
