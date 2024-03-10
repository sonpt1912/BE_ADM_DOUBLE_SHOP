package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.dto.request.EmployeeRequest;
import com.example.be_adm_double_shop.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findUserbyUsername(String username);

    Employee findUserbyEmail(String username);

    Object getAllEmployeeByCondition(EmployeeRequest employeeRequest);

    Object createEmployee(Employee employee, String creator);

    Object updateEmployee(EmployeeRequest employee, String creator);


}
