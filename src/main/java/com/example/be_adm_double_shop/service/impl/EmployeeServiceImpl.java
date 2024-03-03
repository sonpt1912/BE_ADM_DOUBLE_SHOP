package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.ValidationException;
import com.example.be_adm_double_shop.dto.request.EmployeeRequest;
import com.example.be_adm_double_shop.dto.request.MailRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Employee;
import com.example.be_adm_double_shop.repository.EmployeeRepository;
import com.example.be_adm_double_shop.service.EmployeeService;
import com.example.be_adm_double_shop.service.MailService;
import com.example.be_adm_double_shop.util.Constant;
import com.example.be_adm_double_shop.util.DateUtil;
import com.example.be_adm_double_shop.util.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepository userRepository;

    @Autowired
    private MailService mailService;

    @PersistenceContext
    private EntityManager entityManager;

    // NOT CHANGE
    public Employee createUser(Employee user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // NOT CHANGE
    @Override
    public Employee findUserbyUsername(String username) {
        return userRepository.findEmployeeByUsername(username);
    }

    @Override
    public Object getAllEmployeeByCondition(EmployeeRequest employeeRequest) {

        StringBuilder str = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        ListResponse<Employee> listResponse = new ListResponse<>();

        str.append("SELECT * FROM employee e ");
        str.append("WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(employeeRequest.getFullName())) {
            str.append(" AND e.name like CONCAT('%', :name ,'%') ");
            params.put("name", employeeRequest.getFullName());
        }

        if (!StringUtil.stringIsNullOrEmty(employeeRequest.getPhone())) {
            str.append(" AND e.name like CONCAT('%', :phone ,'%') ");
            params.put("phone", employeeRequest.getPhone());
        }
        if (!StringUtil.stringIsNullOrEmty(employeeRequest.getEmail())) {
            str.append(" AND e.email like CONCAT('%', :email ,'%') ");
            params.put("email", employeeRequest.getEmail());
        }
        if (!StringUtil.stringIsNullOrEmty(employeeRequest.getCity())) {
            str.append(" AND e.city like CONCAT('%', :city ,'%') ");
            params.put("city", employeeRequest.getCity());
        }

        if (!StringUtil.stringIsNullOrEmty(employeeRequest.getDistrict())) {
            str.append(" AND e.district like CONCAT('%', :district ,'%') ");
            params.put("district", employeeRequest.getDistrict());
        }

        if (!StringUtil.stringIsNullOrEmty(employeeRequest.getProvince())) {
            str.append(" AND e.province like CONCAT('%', :province ,'%') ");
            params.put("province", employeeRequest.getProvince());
        }

        if (StringUtil.stringIsNullOrEmty(employeeRequest.getPage())) {
            str.append("LIMIT :page, :size");
            if (employeeRequest.getPage() == 0) {
                params.put("page", 0);
            } else {
                params.put("page", (employeeRequest.getPage() * employeeRequest.getPageSize()));
            }
            params.put("size", employeeRequest.getPageSize());
        }

        Query queryList = entityManager.createNativeQuery(str.toString(), Employee.class);
        params.forEach(queryList::setParameter);

        List<Employee> employeeList = queryList.getResultList();

        str.setLength(0);
        params.clear();


        str.append("SELECT COUNT(*) FROM employee e ");
        str.append("WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(employeeRequest.getFullName())) {
            str.append(" AND e.name like CONCAT('%', :name ,'%') ");
            params.put("name", employeeRequest.getFullName());
        }

        if (!StringUtil.stringIsNullOrEmty(employeeRequest.getPhone())) {
            str.append(" AND e.name like CONCAT('%', :phone ,'%') ");
            params.put("phone", employeeRequest.getPhone());
        }
        if (!StringUtil.stringIsNullOrEmty(employeeRequest.getEmail())) {
            str.append(" AND e.phone like CONCAT('%', :email ,'%') ");
            params.put("email", employeeRequest.getEmail());
        }
        if (!StringUtil.stringIsNullOrEmty(employeeRequest.getCity())) {
            str.append(" AND e.city like CONCAT('%', :city ,'%') ");
            params.put("city", employeeRequest.getCity());
        }

        if (!StringUtil.stringIsNullOrEmty(employeeRequest.getDistrict())) {
            str.append(" AND e.district like CONCAT('%', :district ,'%') ");
            params.put("district", employeeRequest.getDistrict());
        }

        if (!StringUtil.stringIsNullOrEmty(employeeRequest.getProvince())) {
            str.append(" AND e.province like CONCAT('%', :province ,'%') ");
            params.put("province", employeeRequest.getProvince());
        }

        Query querySize = entityManager.createNativeQuery(str.toString());
        params.forEach(querySize::setParameter);

        listResponse.setListData(employeeList);
        int totalRecord = ((Number) querySize.getSingleResult()).intValue();
        listResponse.setTotalRecord(totalRecord);
        return listResponse;
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
        if (StringUtil.stringIsNullOrEmty(employee.getId())) {
           throw new ValidationException("Khong tim thay nhan vien");
        }
        employee.setUpdatedTime(DateUtil.dateToString4(new Date()));
        employee.setUpdatedBy(creator);
        return userRepository.save(employee);
    }

    // NOT CHANGE
    @Override
    public Object resetPassword(Employee employee, String updatedBy) {
        mailService.sendMailFortgotPassword();
        return null;
    }

}
