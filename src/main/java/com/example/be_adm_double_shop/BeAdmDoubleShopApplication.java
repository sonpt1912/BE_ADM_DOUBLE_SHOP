package com.example.be_adm_double_shop;

import com.example.be_adm_double_shop.config.EnableWrapResponse;
import com.example.be_adm_double_shop.entity.Employee;
import com.example.be_adm_double_shop.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableWrapResponse
public class BeAdmDoubleShopApplication  {

    public static void main(String[] args) {
        SpringApplication.run(BeAdmDoubleShopApplication.class, args);
    }

}
