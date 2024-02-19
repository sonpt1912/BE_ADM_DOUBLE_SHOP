package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.config.EnableWrapResponse;
import com.example.be_adm_double_shop.entity.Employee;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@EnableWrapResponse
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/create")
    public ResponseEntity create(@RequestHeader("Authorization") String token, @RequestBody Employee employee) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(employeeService.createEmployee(employee, username), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity updateEmplooyee(@RequestHeader("Authorization") String token, @RequestBody Employee employee) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(employeeService.updateEmployee(employee, username), HttpStatus.OK);
    }


    @PostMapping("/reset-password")
    public ResponseEntity resetPassword(@RequestHeader("Authorization") String token, @RequestBody Employee employee) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(employeeService.resetPassword(employee, username), HttpStatus.OK);
    }

}
