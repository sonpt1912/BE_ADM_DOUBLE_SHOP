package com.example.be_adm_double_shop;

import com.example.be_adm_double_shop.config.EnableWrapResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableWrapResponse
public class BeAdmDoubleShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeAdmDoubleShopApplication.class, args);
    }

}
