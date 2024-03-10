package com.example.be_adm_double_shop.dto.request;

import lombok.Data;

@Data

public class EmployeeRequest {

    private Long id;

    private String username;

    private String fullName;

    private String phone;

    private String password;

    private String email;

    private String city;

    private String province;

    private String district;

    private Integer status;

    private Integer gender;

    private int page;

    private int pageSize;

}
