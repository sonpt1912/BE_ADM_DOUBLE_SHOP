package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findEmployeeByUsername(String username);

}
