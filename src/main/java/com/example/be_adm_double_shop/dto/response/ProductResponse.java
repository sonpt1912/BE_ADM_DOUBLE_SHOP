package com.example.be_adm_double_shop.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {

    private String productName;

    private String brandName;

    private String collarName;

    private String categoryName;

    private String materialName;

    private String colorName;

    private String sizeName;

}
