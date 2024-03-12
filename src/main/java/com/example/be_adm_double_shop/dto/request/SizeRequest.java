package com.example.be_adm_double_shop.dto.request;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SizeRequest {

    private Long page;

    private Long pageSize;

    private String name;

    private Long status;

    private String code;

    private String createdBy;

    private String createdTime;

    // cho luồng sản phẩm
    private List<ColorRequest> listColor;

}
