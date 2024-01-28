package com.example.be_adm_double_shop.dto.request;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRequest {
    private String name;
    private String phone;
    private Date birtDay;
    private String createdBy;
    private String email;
    private Integer gender;
    private Long pageSize;
    private String image;


}
