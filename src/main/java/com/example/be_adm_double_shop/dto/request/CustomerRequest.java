package com.example.be_adm_double_shop.dto.request;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRequest {
    private Long page;
    private String phone;
    private Integer status;
    private Long pageSize;

}
