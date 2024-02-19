package com.example.be_adm_double_shop.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ColorRequest {
    private Long page;

    private Long pageSize;

    private String name;

    private Long status;

    private String code;

}
