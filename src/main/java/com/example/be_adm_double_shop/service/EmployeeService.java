package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.Employee;

public interface EmployeeService {

    Employee createUser(Employee user);

    Employee findUserbyUsername(String username);

}
