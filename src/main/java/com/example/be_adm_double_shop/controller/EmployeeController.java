package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.entity.Employee;
import com.example.be_adm_double_shop.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/double/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/index")
    public ResponseEntity<Page<Employee>> getAllEmployees(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        if (page < 1) page = 1;
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<Employee> employeePage = employeeService.getAllEmployees(pageable);
        return ResponseEntity.ok().body(employeePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody @Valid Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok().body(savedEmployee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable("id") Long id,
            @RequestBody @Valid Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok().body(updatedEmployee);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployees(@RequestParam("keyword") String keyword) {
        List<Employee> employeeList = employeeService.searchEmployees(keyword);
        return ResponseEntity.ok().body(employeeList);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Employee>> getSortedEmployeeList() {
        List<Employee> sortedEmployeeList = employeeService.sapXepTheoCode();
        return ResponseEntity.ok().body(sortedEmployeeList);
    }
}
