package com.example.be_adm_double_shop.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressRequest {

    private String city;
    private Long pageSize;



}
