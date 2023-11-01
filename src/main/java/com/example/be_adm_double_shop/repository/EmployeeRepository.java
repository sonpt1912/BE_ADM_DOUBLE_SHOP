package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT MAX(e.id) FROM Employee e")
    Long findMaxId();
    List<Employee> findByCodeContainingIgnoreCaseOrNameContainingIgnoreCase(String code, String name);
    List<Employee> findAllByOrderByCode();
    // Truy vấn để tìm kiếm nhân viên theo giới tính, trạng thái, số điện thoại, email và mã nhân viên
    List<Employee> findByGenderAndStatusAndPhoneContainingAndEmailContainingAndCodeContainingIgnoreCase(
            int gender, int status, String phone, String email, String code);
    List<Employee> findByName(String name);
    List<Employee> findByEmailAndStatus(String email, int status);
    List<Employee> findByGenderAndStatus(int gender, int status);
    List<Employee> findByGenderAndStatusAndNameContainingIgnoreCase(int gender, int status, String name);
    List<Employee> findAllByOrderByNameAscStatusDesc();
    List<Employee> findByGenderAndStatusOrderByCode(int gender, int status);
    List<Employee> findByGenderAndStatusOrderByCodeDesc(int gender, int status);
    List<Employee> findByOrderByCreatedTimeDesc();
    List<Employee> findByOrderByCreatedTimeAsc();


}
