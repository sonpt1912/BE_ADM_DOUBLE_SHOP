package com.example.be_adm_double_shop.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data

public class PromotionRequest {

    private Long page;

    private Long pageSize;

    private String name;

    private Integer status;

    private String code;

    private String createdBy;

    private String createdTime;



    private Long discountAmount;

    private Integer discountPercent;

    private String startDate;

    private String endDate;

    private String updatedBy;

    private String updatedTime;

    private List<Long> detailProduct;
}
