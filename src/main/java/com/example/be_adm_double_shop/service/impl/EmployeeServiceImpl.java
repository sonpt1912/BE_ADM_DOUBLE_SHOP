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

import java.util.*;

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

    // NOT CHANGE
    @Override
    public Employee findUserbyUsername(String username) {
        return userRepository.findEmployeeByUsername(username);
    }

    @Override
    public Employee findUserbyEmail(String email) {
        return userRepository.findEmployeeByEmail(email);
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

        str.append(" ORDER BY e.created_time ");

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

        List<String> fullName = Arrays.stream(employee.getName().split(" ")).toList();
        String username = fullName.get(fullName.size() - 1);
        for (int i = 0; i < fullName.size(); i++) {
            if (fullName.get(i).equals(fullName.get(fullName.size() - 1))) {
                break;
            }
            username += fullName.get(i).charAt(0);
        }
        while (true) {
            username += (int) (Math.random() * 10);
            if (StringUtil.stringIsNullOrEmty(userRepository.findEmployeeByUsername(employee.getUsername()))) {
                break;
            }
        }
        employee.setCreatedBy(creator);
        employee.setStatus(Constant.ACTIVE);
        employee.setUsername(username.toLowerCase(Locale.ROOT));
        employee.setCreatedTime(DateUtil.dateToString(new Date(), DateUtil.FORMAT_DATE_TIME4));
        employee.setRole(Constant.IS_EMPLOYEE);
        String password = Constant.DEFAULT_PASSWORD;
        if (!StringUtil.stringIsNullOrEmty(employee.getPassword())) {
            password = employee.getPassword();
        }
        employee.setPassword(passwordEncoder.encode(password));
        try {
            userRepository.save(employee);
            MailRequest mailRequest = MailRequest.builder()
                    .reciver(employee.getEmail())
                    .content(password)
                    .subContent(employee.getUsername())
                    .build();
            mailService.sendMailCreateAccount(mailRequest);
            return Constant.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    @Override
    public Object updateEmployee(EmployeeRequest request, String updateBy) {

        if (StringUtil.stringIsNullOrEmty(request.getId())) {
            throw new ValidationException("Truong id khong duoc de trong");
        }

        if (StringUtil.stringIsNullOrEmty(userRepository.findEmployeeById(request.getId()))) {
            throw new ValidationException("Khong tim thay nhan vien");
        }

        Employee employee = userRepository.findEmployeeById(request.getId());

        if (request.getPhone() != null) {
            employee.setPhone(request.getPhone());
        }

        if (request.getStatus() != null) {
            employee.setStatus(request.getStatus());
        }

        employee.setUpdatedBy(updateBy);
        employee.setUpdatedTime(DateUtil.dateToString4(new Date()));

        return userRepository.save(employee);
    }

    // NOT CHANGE

}
