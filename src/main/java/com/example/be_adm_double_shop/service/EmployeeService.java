package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.Employee;
import com.example.be_adm_double_shop.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setCode(employee.getCode());
            existingEmployee.setName(employee.getName());
            existingEmployee.setGender(employee.getGender());
            existingEmployee.setPhone(employee.getPhone());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPassword(employee.getPassword());
            existingEmployee.setDistrict(employee.getDistrict());
            existingEmployee.setProvice(employee.getProvice());
            existingEmployee.setCity(employee.getCity());
            existingEmployee.setGender(employee.getGender());
            existingEmployee.setBirthDay(employee.getBirthDay());
            existingEmployee.setRole(employee.getRole());
            existingEmployee.setStatus(employee.getStatus());
            existingEmployee.setCreatedBy(employee.getCreatedBy());
            existingEmployee.setUpdatedBy(employee.getUpdatedBy());
            existingEmployee.setCreatedTime(employee.getCreatedTime());
            existingEmployee.setUpdatedTime(employee.getUpdatedTime());

            return employeeRepository.save(existingEmployee);
        }
        return null;
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> searchEmployees(String keyword) {
        return employeeRepository.findByCodeContainingIgnoreCaseOrNameContainingIgnoreCase(keyword, keyword);
    }

    public List<Employee> sapXepTheoCode() {
        return employeeRepository.findAllByOrderByCode();
    }
}
