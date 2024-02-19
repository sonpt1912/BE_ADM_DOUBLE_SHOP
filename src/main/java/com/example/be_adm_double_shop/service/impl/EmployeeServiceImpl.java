package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.request.MailRequest;
import com.example.be_adm_double_shop.entity.Employee;
import com.example.be_adm_double_shop.repository.EmployeeRepository;
import com.example.be_adm_double_shop.service.EmployeeService;
import com.example.be_adm_double_shop.service.MailService;
import com.example.be_adm_double_shop.util.Constant;
import com.example.be_adm_double_shop.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepository userRepository;

    @Autowired
    private MailService mailService;

    public Employee createUser(Employee user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Employee findUserbyUsername(String username) {
        return userRepository.findEmployeeByUsername(username);
    }

    @Override
    public Object createEmployee(Employee employee, String creator) {
        employee.setCreatedBy(creator);
        employee.setStatus(Constant.ACTIVE);
        employee.setCreatedTime(DateUtil.dateToString(new Date(), DateUtil.FORMAT_DATE_TIME4));
        employee.setRole(Constant.IS_EMPLOYEE);
        employee.setUsername(employee.getEmail());
        employee.setPassword(passwordEncoder.encode(Constant.DEFAULT_PASSWORD));
        try {
            userRepository.save(employee);
            MailRequest mailRequest = MailRequest.builder()
                    .content(Constant.DEFAULT_PASSWORD)
                    .reciver(employee.getUsername())
                    .build();
            mailService.sendMailCreateAccount(mailRequest);
            return Constant.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    @Override
    public Object updateEmployee(Employee employee, String creator) {
        employee.setUpdatedTime(DateUtil.dateToString4(new Date()));
        employee.setUpdatedBy(creator);
        return userRepository.save(employee);
    }

    @Override
    public Object resetPassword(Employee employee, String updatedBy) {
        mailService.sendMailFortgotPassword();
        return null;
    }

}
