package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.entity.Employee;
import com.example.be_adm_double_shop.repository.EmployeeRepository;
import com.example.be_adm_double_shop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepository userRepository;

    public List<Employee> getUser() {
        return userRepository.findAll();
    }

    public Employee createUser(Employee user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
