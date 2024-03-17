package com.example.be_adm_double_shop.dto.request;

import lombok.Data;

@Data

public class MaterialRequest {

    private Long id;

    private Long page;

    private Long pageSize;

    private String name;

    private Long status;

    private String code;

    private String createdBy;

    private String createdTime;
}
