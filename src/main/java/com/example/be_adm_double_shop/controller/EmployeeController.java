package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.config.EnableWrapResponse;
import com.example.be_adm_double_shop.dto.request.EmployeeRequest;
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
    public ResponseEntity createEmployee(@RequestHeader("Authorization") String token, @RequestBody Employee employee) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(employeeService.createEmployee(employee, username), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity updateEmplooyee(@RequestHeader("Authorization") String token, @RequestBody EmployeeRequest employee) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(employeeService.updateEmployee(employee, username), HttpStatus.OK);
    }


    @PostMapping("/get-all-employee-by-condition")
    public ResponseEntity getAllEmployeeByCondition(@RequestBody EmployeeRequest employeeRequest) {
        return new ResponseEntity(employeeService.getAllEmployeeByCondition(employeeRequest), HttpStatus.OK);
    }

}
