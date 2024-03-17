package com.example.be_adm_double_shop.dto;

import com.example.be_adm_double_shop.entity.DetailPromotion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class PromotionDTO {
    private String code;

    private String name;

    private Long discountAmount;

    private Integer discountPercent;

    private Integer status;

    private String startDate;

    private String endDate;

    private String createdBy;

    private String updatedBy;

    private String createdTime;

    private String updatedTime;

    private List<Long> detailProduct;


}
