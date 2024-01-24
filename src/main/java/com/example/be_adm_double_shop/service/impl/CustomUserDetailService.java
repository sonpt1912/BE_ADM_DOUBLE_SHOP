package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.entity.Employee;
import com.example.be_adm_double_shop.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private EmployeeRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = userRepository.findEmployeeByUsername(username);
        return user;
    }

}
