package com.example.be_adm_double_shop.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VoucherRequest {
    private Long page;

    private Long pageSize;

    private Long status;

    private String code;

    private String name;

    private String startDate;

    private String endDate;

    private Long quantity;

    private Long discountAmount;

}
